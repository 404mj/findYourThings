package me.lostandfound.action;

import me.lostandfound.model.*;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import org.jboss.seam.security.Credentials;


import java.util.Arrays;
import java.util.List;
import java.util.Collections;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.Integer;
import java.lang.Object;
@Name("updateCountUser")
public class UpdateCountUser {

	private static final String search="select u from Users u where u.name=:username";
	private static final String searchTop10="select messagess.count from Messagess messagess";
	private static final String searchMess="select messagess from Messagess messagess where messagess.count>=:count2 and messagess.count<=:count1 order by messagess.count desc";
	private int count1;   //定义count1变量，便于从数据库中查询消息的值赋给它
	private int count2;   //同上

	
	@In
	Credentials credentials;
	@In
	EntityManager entityManager;
	 Users users = new Users();

	public UpdateCountUser(){
		
	}
    
    //查询是否为登录用户，如果是则返回登录用户对象，如果不是则返回空值
	public Users makeId(){
		String username=credentials.getUsername();
		if(username!=null){
		users= (Users)entityManager.createQuery(search).setParameter("username",username).getSingleResult();
		return 	users;
		}
		else{
			return null;
		}

	}

/*查询消息点击率在前十名的消息*/
	public List<Messagess> searchTop10(){
	List<Integer> list =entityManager.createQuery(searchTop10).getResultList();//查询获得所有的消息
	/*for (Integer write :list ) {
		System.out.println(write);
	}*/
	
	/*Integer[] countmess=new Integer[list.size()];
	  for (int w=0;w<list.size() ;w++ ) {
	  	countmess[w]=list.get(w);
	  }
         for (int i=0;i<countmess.length-1 ;i++ ) {
         	for (int j=i+1;j<countmess.length ;j++ ) {
         		if (countmess[j]>countmess[i]) {
         			int temp=countmess[i];
         		countmess[i]=countmess[j];
         		countmess[j]=temp;
         		}
         		
         	}
         }
         Integer[] countnew=new Integer[10];
         for (int k=0;k<10 ;k++ ) {
         	countnew[k]=countmess[k];
         }
         count1=countnew[0];
         count2=countnew[9];
      List<Messagess>list1=entityManager.createQuery(searchMess).setParameter("count1",count1).setParameter("count2",count2).getResultList();
      return list1;*/


      if(list.size()!=0){
      Collections.sort(list,Collections.reverseOrder());//Collections接口的sort方法对list按照倒序排序
      count1=list.get(0);  //获得list的第一个元素
      count2=list.get(list.size()-1); //获得list的最后一个元素
     Query query = entityManager.createQuery(searchMess);  //查询阅读量排名前十的消息
     query.setParameter("count1",count1);   //对sql参数赋值
     query.setParameter("count2",count2);    //对sql参数赋值
     query.setFirstResult(0);   //设置query里面的起始查询位置
     query.setMaxResults(9);       //设置最大查询数
     List<Messagess> list1=(List<Messagess>)query.getResultList(); //获得查询的结果
       /*List<Messagess>list1=entityManager.createQuery(searchMess).setParameter("count1",count1).setParameter("count2",count2).getResultList();
       for (Messagess write1 :list1 ) {
		System.out.println(write1);
	}*/
      return list1;  //返回这个查询结果
     }
      else{
  	   return null;     //没有值则返回空
     }


	}

	public int getCount1(){
         return count1;
	}

	public void setCount1(int count1){
		this.count1=count1;
	}

	public int getCount2(){
         return count2;
	}

	public void setCount2(int count2){
		this.count2=count2;
	}



}
