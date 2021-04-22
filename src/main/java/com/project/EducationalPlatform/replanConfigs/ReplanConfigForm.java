package com.project.EducationalPlatform.replanConfigs;

import java.util.ArrayList;
import java.util.List;

public class ReplanConfigForm {
	private List<ReplanConfig> configs;
	
	public ReplanConfigForm() {
		configs = new ArrayList<ReplanConfig>();
	}
	
	public void addConfig(ReplanConfig config) {
		configs.add(config);
	}

	public List<ReplanConfig> getConfigs() {
		return configs;
	}

	public void setConfigs(List<ReplanConfig> configs) {
		this.configs = configs;
	}
}
