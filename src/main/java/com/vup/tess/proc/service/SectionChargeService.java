package com.vup.tess.proc.service;

import com.vup.tess.model.SectionChargeInfo;

public interface SectionChargeService {

	SectionChargeInfo selectCharge(String factoryGroupId, String energyGroupId, String now, String codeId);

}
