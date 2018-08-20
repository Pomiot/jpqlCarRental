package com.capgemini.service;

import com.capgemini.types.WorkerTO;

public interface WorkerService {
	WorkerTO addWorker(WorkerTO worker);
	
	WorkerTO findWorkerById(Long workerId);
}
