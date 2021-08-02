package com.devplatform.service;

import com.devplatform.entity.Milestone;
import com.devplatform.lang.Result;

public interface MilestoneService {

    Result getAllMilestoneByProjectid(String projectid);

    Result saveMilestone(Milestone milestone);
}
