package me.lostandfound.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import me.lostandfound.model.Messagess;
import me.lostandfound.model.Users;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.security.Credentials;
@Name("hiddenStatus")
public class hiddenStatus {
	private Users messagessUser;
	private String leaveMessage;
	
	UsersList usersList=new UsersList();
	
	@In
	EntityManager entityManager;

	@In(create=true)
	Messagess messagess;
	
	@In
	Credentials credentials;
	
	String userName=credentials.getUsername();
	
	List<Messagess> list01=new ArrayList<Messagess>();
	
	public Date createDate(){
		
		Date date=new Date(0);
		
		return date;
		
		
	}
	
	
	
	/*
	@Transactional
	public String persistVisitor(){
		entityManager.joinTransaction();	
		VisitorBook visitorBook=new VisitorBook();
		visitorBook.setUsers(messagessUser);
		visitorBook.setLeaveMessage(leaveMessage);
		visitorBook.setUsers(messagessUser);
		entityManager.persist(visitorBook);
		entityManager.flush();

		
		
		return "persisted";
	}
	*/
	
	
	/*
	public List<Messagess> findMessagess(){
	
		
	//	@In
		//EntityManager entityManager;

	 //  Messagess messagess =new Messagess();
		
	//	@In
		//Credentials credentials;
		
		//String userName=credentials.getUsername();
		
		//List<Messagess> list01=new ArrayList<Messagess>();
		List<Messagess> list=entityManager.createQuery("select m from Messagess m").getResultList();
		String name=userName;
		for(Messagess m:list){
			String str1=m.getText();
			String str2=str1.trim().replace("name", ",.:;><::::::`!****&&^&**125455");
			if(!(str1.equals(str2))){
				list01.add(m);
			}
		}
		
		
			return list01;
		
	}
	
	
	*/
	
	/*
	public boolean isShowVisitorBook(){
		if(usersList.makeId().getName().equals(creadentials.getUsername())){
			return false;
		}
		return true;
	}
	
	*/
	public Users getMessagessUser() {
		return messagessUser;
	}
	public void setMessagessUser(Users messagessUser) {
		this.messagessUser = messagessUser;
	}
	
	/*
	public Users getLiuyanUser() {
		return liuyanUser;
	}
	public void setLiuyanUser(Users liuyanUser) {
		this.liuyanUser = liuyanUser;
	}
	
	*/

	public String getLeaveMessage() {
		return leaveMessage;
	}

	public void setLeaveMessage(String leaveMessage) {
		this.leaveMessage = leaveMessage;
	}
	
	
	
}
