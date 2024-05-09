package supermarket;

import java.util.HashMap;

public class IDandPasswords {
HashMap<String,String> admininfo = new HashMap<String,String>();
HashMap<String,String> cashierinfo = new HashMap<String,String>();
	
	
	IDandPasswords(){
		
		admininfo.put("admin", "123@adm");
		cashierinfo.put("cashier", "cash@pesa");
		
	}
	 protected HashMap getAdmininfo(){
		return admininfo;
	}
	 protected HashMap getCashierinfo() {
		 return cashierinfo;
	 }
}


