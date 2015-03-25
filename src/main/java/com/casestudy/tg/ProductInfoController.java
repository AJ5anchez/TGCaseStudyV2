package com.casestudy.tg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
/***
 * Controller that defines various REST end-points
 * @author AJ Sanchez (a.sanchez.824@gmail.com)
 *
 */
public class ProductInfoController {
	
	@Autowired
	PriceEntryRepository priceRepository;
	
	@RequestMapping(value = "/products/{pid}", method = RequestMethod.GET)
	public ProductInfo getProductInfo(@PathVariable long pid){
		
		// get price entry from MongoDB
		PriceEntry entry = priceRepository.findByPid(pid);
		if (entry == null) {
			String message = "ERROR: Price not found for pid = " + pid;
			throw new PriceNotFoundException(message);
		}
		
		// get name from service
		String name = getName(pid);
		if (name == null){
			String message = "ERROR: Name not found for pid = " + pid;
			throw new NameNotFoundException(message);
		}
		
		// all OK
		return new ProductInfo(pid, name, entry.getCurrentPrice());
		
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	static class PriceNotFoundException extends RuntimeException {

		public PriceNotFoundException(String message) {
			super(message);
		}		
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	static class NameNotFoundException extends RuntimeException {

		public NameNotFoundException(String message) {
			super(message);
		}
	}
	

	/***
	 * This helper method connects to an external
	 * service from which the name of the product
	 * identified by pid can be extracted
	 * @param pid product id
	 * @return
	 */
	private String getName(long pid) {
		
		try {
			
			// form the URL
			String prefix = "https://api.target.com/products/v3/";
			String spid = Long.toString(pid);
			String suffix = "?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz";
			URL url = new URL(prefix + spid + suffix);
			
			// get the result
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			
			// read lines looking for the name
			String anchor = "\"online_description\":{\"value\":\"";
			String line;
			while ((line = reader.readLine()) != null){
				// typically there will be only one line in the response
				// NOT checking for multi-lines
				return extractName(line, anchor);
			}
		}
		catch(Exception ex){
			return null;
		}
		return null;
	}


	/***
	 * This helper method returns the sub-string of line whose prefix
	 * is anchor, and whose past-the-end character is '\"'
	 * <br>
	 * Note: I am sure there might be more elegant ways to do
	 * this using JSON-related libraries ...
	 * @param line string containing (potentially) the name
	 * @param anchor string that helps define the beginning of the name (if any)
	 * @return
	 */
	private String extractName(String line, String anchor) {
		
		// index where the anchor begins
		int begins = line.indexOf(anchor);
		
		// anchor not found
		if (begins < 0)
			return null;
		
		// anchor found ... determine limits of word
		int from = begins + anchor.length();
		int to = line.indexOf("\"", from);
		
		// word delimited by [from, to)
		return line.substring(from, to);
		
	}
	
}

//T.B.T.G
