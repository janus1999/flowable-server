package org.janus.system.service;

import org.janus.system.model.Org;

public interface OrgService {
	
	public int insertOrg(Org org);
	
	public Org selectById(String id);
}
