package com.parameters;

import com.pages.RailwaysPage;

public class Context {
	private static RailwaysPage railwaysPage;

	public static RailwaysPage getRailwaysPage() {
		return railwaysPage;
	}

	public static void setRailwaysPage(RailwaysPage railwaysPage) {
		Context.railwaysPage = railwaysPage;
	}
}