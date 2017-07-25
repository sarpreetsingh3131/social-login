package sociallogin.sarpreetsingh;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
@RequestMapping("/")
public class FacebookController {

	private Facebook facebook;
	private ConnectionRepository connectionRepository;

	public FacebookController(Facebook facebook, ConnectionRepository connectionRepository) {
		this.facebook = facebook;
		this.connectionRepository = connectionRepository;
	}
	
	@GetMapping
	public String login(Model model) throws JsonProcessingException {
		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			return "redirect:/connect/facebook";
		}
		String[] fields = { "id", "email", "name" };
		User profile = facebook.fetchObject("me", User.class, fields);
		model.addAttribute("facebookProfile", profile);
		return "hello";
	}
}