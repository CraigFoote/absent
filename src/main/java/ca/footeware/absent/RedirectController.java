package ca.footeware.absent;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class RedirectController {

	/**
	 * A request to '/name/someName' will return a '301 Moved Permanently' to '/name/redirect/someName'.
	 * 
	 * @param name {@link String}
	 * @return {@link RedirectView}
	 */
	@GetMapping(value = "/name/{name}")
	public RedirectView getName(@PathVariable String name) {
		String newUrl = "/name/redirect/" + name;
		RedirectView redirectView = new RedirectView(newUrl);
		redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
		return redirectView;
	}

	/**
	 * A request to '/name/redirect/someName' will return a '200 OK' with content 'someName'.
	 * 
	 * @param name {@link String}
	 * @return {@link ResponseEntity}
	 */
	@GetMapping(value = "/name/redirect/{name}")
	public ResponseEntity<String> getRedirectedName(@PathVariable String name) {
		return ResponseEntity.ok(name);
	}
}
