package duong.dev.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class SessionService {
	@Autowired
	HttpSession session;
	public void setAttribute(String name, Object value) {
		session.setAttribute(name, value);
	}
	
	public <T> T getSession(String name) {
		return (T) session.getAttribute(name);
	}
	public void removeAttribute(String name) {
		session.removeAttribute(name);
	}

}
