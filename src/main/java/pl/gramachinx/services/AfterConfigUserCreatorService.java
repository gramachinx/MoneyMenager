package pl.gramachinx.services;

import pl.gramachinx.domains.FirstConfig;

public interface AfterConfigUserCreatorService {
	void fullConfigUser(FirstConfig firstConfig, String username);

}
