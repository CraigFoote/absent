# absent

A REST service with two endpoints designed to facilitate testing server redirect and basic authentication.

## End Points

1. `/name/{name}` : Returns a `301 Moved Permanently` status with `Location` header set to `/name/redirect/{name}`.

1. `/name/redirect/{name}` : Returns a `200 OK` status with content `{name}`.

1. Basic authentication was added making the endpoints above password-protected (username is `spring` and password is `boot`).

## Running

Check out, build and run this Spring Boot application. Then open this page in a browser: [swagger](http://localhost:8080/swagger-ui/index.html).


Or in a console:

### `/name/{name}`

``` bash
❯ curl -v -u spring:boot http://localhost:8080/name/bob
* Host localhost:8080 was resolved.
* IPv6: ::1
* IPv4: 127.0.0.1
*   Trying [::1]:8080...
* Established connection to localhost (::1 port 8080) from ::1 port 54526 
* using HTTP/1.x
* Server auth using Basic with user 'spring'
> GET /name/bob HTTP/1.1
> Host: localhost:8080
> Authorization: Basic c3ByaW5nOmJvb3Q=
> User-Agent: curl/8.18.0
> Accept: */*
> 
* Request completely sent off
< HTTP/1.1 301 
< Location: /name/redirect/bob
< X-Content-Type-Options: nosniff
< X-XSS-Protection: 0
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
< Expires: 0
< X-Frame-Options: DENY
< Content-Language: en-CA
< Content-Length: 0
< Date: Wed, 30 Sep 2026 02:19:30 GMT
< 
* Connection #0 to host localhost:8080 left intact
```

Note the two lines indicating redirect:

``` bash
HTTP/1.1 301 
Location: /name/redirect/bob
```

### `/name/redirect/{name}`

``` bash
❯ curl -v -u spring:boot http://localhost:8080/name/redirect/bob
* Host localhost:8080 was resolved.
* IPv6: ::1
* IPv4: 127.0.0.1
*   Trying [::1]:8080...
* Established connection to localhost (::1 port 8080) from ::1 port 38614 
* using HTTP/1.x
* Server auth using Basic with user 'spring'
> GET /name/redirect/bob HTTP/1.1
> Host: localhost:8080
> Authorization: Basic c3ByaW5nOmJvb3Q=
> User-Agent: curl/8.18.0
> Accept: */*
> 
* Request completely sent off
< HTTP/1.1 200 
< X-Content-Type-Options: nosniff
< X-XSS-Protection: 0
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
< Expires: 0
< X-Frame-Options: DENY
< Content-Type: text/plain;charset=UTF-8
< Content-Length: 3
< Date: Wed, 30 Sep 2026 02:24:03 GMT
< 
* Connection #0 to host localhost:8080 left intact
bob
```
Note the 200 status and 'bob' response.

A browser will automatically follow the redirect and you'll immediately receive the response 'bob'. As shown above, curl does not follow the redirect. You have to call the redirect URL to get the 'bob' response.

This project was created to investigate using libsoup in a GNOME Shell extension and giving the option to ignore redirects. It turns out libsoup by default follows redirects but there is a flag you can set at message creation to ignore redirects. Problem solved!

Another use-case came along: support custom HTTP request headers. I added the `SecurityConfig` class to enable basic auth testing.

---

