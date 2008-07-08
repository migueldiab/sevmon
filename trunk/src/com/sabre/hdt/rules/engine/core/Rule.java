package com.sabre.hdt.rules.engine.core;

import com.sabre.hdt.entities.Activity;

public interface Rule {
	public double execute(Activity activity);
}
