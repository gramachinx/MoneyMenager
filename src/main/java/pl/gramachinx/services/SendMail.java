package pl.gramachinx.services;

import pl.gramachinx.domains.User;

public interface SendMail {
		boolean sendMail(String username);
		void passRecorvery(String email, User user);

}
