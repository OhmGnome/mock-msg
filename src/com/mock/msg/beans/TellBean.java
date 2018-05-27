package com.mock.msg.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mock.msg.model.Tell;
/*
 * holds a static tell that can live across beans
 */
@Component
@Scope("session")
public class TellBean {
	
	private Tell tell;

	public Tell getTell() {
		return tell;
	}

	public void setTell(Tell tell) {
		this.tell = tell;
	}

}
