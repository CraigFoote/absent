package ca.footeware.absent;

import java.util.Enumeration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/name")
public class RedirectController {

	/**
	 * Print to console the key:value pairs in the provided request headers.
	 * 
	 * @param request {@link HttpServletRequest}
	 */
	private void checkRequestHeaders(HttpServletRequest request) {
		System.out.println(request.getAuthType());
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = headerNames.nextElement();
			String value = request.getHeader(key);
			System.out.println(key + "=" + value);
		}
	}

	/**
	 * A request to '/name/someName' will return a '301 Moved Permanently' to
	 * '/name/redirect/someName'.
	 *
	 * @param name {@link String}
	 * @return {@link RedirectView}
	 */
	@GetMapping(value = "/{name}")
	public RedirectView getName(@PathVariable String name, HttpServletRequest request) {
		String newUrl = "/name/redirect/" + name;
		RedirectView redirectView = new RedirectView(newUrl);
		redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
		checkRequestHeaders(request);
		return redirectView;
	}

	/**
	 * A request to '/name/redirect/someName' will return a '200 OK' with content
	 * 'someName'.
	 *
	 * @param name {@link String}
	 * @return {@link ResponseEntity}
	 */
	@GetMapping(value = "/redirect/{name}")
	public ResponseEntity<String> getRedirectedName(@PathVariable String name) {
		return ResponseEntity.ok(name);
	}
}
