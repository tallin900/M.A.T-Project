package application;

import java.util.ArrayList;

import application.ClientMsg.QueryType;

public class ClientMsg {
	
	public enum QueryType {
		INSERT, DELETE, UPDATE, SELECT
	}
	
	private String query;
	private QueryType queryType;
	
	
	
	public String getQuery() {
		return query;
	}



	public QueryType getQueryType() {
		return queryType;
	}



	public void setQuery(String query) {
		this.query = query;
	}



	public void setQueryType(QueryType queryType) {
		this.queryType = queryType;
	}



	@Override
	public String toString(){
		return queryType.toString() + " query";
	}
}
