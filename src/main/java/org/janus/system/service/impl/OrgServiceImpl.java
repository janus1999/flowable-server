package org.janus.system.service.impl;

import org.janus.system.dao.OrgMapper;
import org.janus.system.model.Org;
import org.janus.system.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class OrgServiceImpl implements OrgService {

	@Autowired
	protected OrgMapper orgMapper;
	
	public int insertOrg(Org org) {
		return orgMapper.insert(org);
	}
	
	public Org selectById(String id){
		return orgMapper.selectById(id);
	}
}
