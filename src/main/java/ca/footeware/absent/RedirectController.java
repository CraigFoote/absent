package ca.footeware.absent;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RedirectController {

	/**
	 * A request to '/someName' will return a '301 Moved Permanently' to '/redirect/someName'.
	 * 
	 * @param name {@link String}
	 * @return {@link RedirectView}
	 */
	@GetMapping(value = "/{name}")
	public RedirectView getName(@PathVariable String name) {
		String newUrl = "/redirect/" + name;
		RedirectView redirectView = new RedirectView(newUrl);
		redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
		return redirectView;
	}

	/**
	 * A request to '/redirect/someName' will return a '200 OK' with content 'someName'.
	 * 
	 * @param name {@link String}
	 * @return {@link ResponseEntity}
	 */
	@GetMapping(value = "/redirect/{name}")
	public ResponseEntity<String> getRedirectedName(@PathVariable String name) {
		return ResponseEntity.ok(name);
	}
}
