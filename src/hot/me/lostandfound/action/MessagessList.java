package me.lostandfound.action;

import me.lostandfound.model.*;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.framework.EntityQuery;
import org.jboss.seam.security.Credentials;
import java.lang.Integer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Name("messagessList")
public class MessagessList extends EntityQuery<Messagess> {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static final String EJBQL = "select messagess from Messagess messagess";

	// 修改匹配text bug.messagesort 是匹配丢失类别
	// 修改RESTRICTIONS 的匹配规则解决搜索详细信息的bug
	private static final String[] RESTRICTIONS = {
			"lower(messagess.palce) like lower(concat('%',#{messagessList.messagess.palce},'%'))",
			"lower(messagess.text) like lower(concat('%',#{messagessList.messagess.text},'%'))",
			"lower(messagess.type) like lower(concat(#{messagessList.messagess.type},'%'))",
			"messageSort.id = #{messagessList.messagess.messageSort.id}",
			"messagess.time > #{messagessList.timeBegin}",
			"messagess.time < #{messagessList.timeEnd}" };

	private Messagess messagess = new Messagess();

	private UsersList usersList=new UsersList();//add usersList constructor

	private String removedId;  //要删除消息对应的id
	private String withUserName;  //消息发布者的登录名

	private Date timeBegin;// 数据类型问题
	private Date timeEnd;

	private String status; 
	private Integer messageWithUserId;  //消息发布者的id


	public MessagessList() {
		setOrderColumn("messagess.id");
		setOrderDirection("desc");
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(30);
	}

	/*
	 * @Override public String getEjbql() { String query = EJBQL + ""; //
	 * info("++++++++++++++++传来的时间参数:" + timeBegin + timeBegin); if (!(null ==
	 * timeBegin) && !(null == timeEnd)) { query += " where messagess.time<='" +
	 * timeEnd + "' and messagess.time>='" + timeBegin + "' ;";
	 * info(">>>>>>>>>>>>>>>>>>>>>>>>>>return query >>>>" + query); return
	 * query; } info(".................................传来了空值"); return null;
	 * 
	 * }
	 */

	public Messagess getMessagess() {
		if (null == messagess.getMessageSort())
			messagess.setMessageSort(new MessageSort());
		return messagess;
	}

	// 重新设计以适应时间搜索，暂时方法,可能会对浏览器不友好--.xml对浏览器友好了
	// 数据量大的话可能会使显示条数不够
	@Override
	public List<Messagess> getResultList() {
		// info("---------------" + getMessagess().getMessageSort().getId());
		// info(">>>>>>>>>>>>>start search for return result>>>>>>>>>>>>>>>>");
		//
		// List<Messagess> superResult = super.getResultList();
		// List<Messagess> newResult = new ArrayList<Messagess>();
		/*
		 * 优化算法 if (!(timeBegin == null) && !(timeEnd == null)) { for (Messagess
		 * me : superResult) { Date timePre; timePre = sdf.parse(timeBegin);//
		 * 起始时间 Date timeLast = sdf.parse(timeEnd);// 结束时间 if
		 * (me.getTime().getTime() > timePre.getTime() && me.getTime().getTime()
		 * < timeLast.getTime()) { newResult.add(me); } } } else if (timeBegin
		 * == null) { }
		 */
		// for (Messagess me : superResult) {
		// Long meTime = me.getTime().getTime();
		// info("+++++++++++++++++++++++++timeBegin and timeEnd" + timeBegin
		// + timeEnd);
		// // Long time1 = sdf.parse(timeBegin).getTime();
		// // Long time2 = sdf.parse(timeEnd).getTime(); avoiding
		// if (timeBegin != null && timeEnd != null) {
		// if (meTime > timeBegin.getTime() && meTime < timeEnd.getTime()) {
		// // info("执行添加两个时间方法");
		// newResult.add(me);
		// }
		// }
		// if (timeBegin != null && timeEnd == null) {
		// if (meTime > timeBegin.getTime()) {
		// newResult.add(me);
		// }
		// }
		// if (timeEnd != null && timeBegin == null) {
		// if (meTime < timeEnd.getTime()) {
		// newResult.add(me);
		// // continue;
		// }
		// }
		// // info("不需要进行时间搜索，因为传来的时间参数是null");
		// // break;
		// }
		// info("///////////////////执行到了这里了，需要返回值 ,为空吗::::::"
		// + newResult.isEmpty());
		// return newResult.isEmpty() ? superResult : newResult;
		// return superResult;
		return super.getResultList();
	}
   //删除自己发布的消息
	@Transactional
	public void removedMessagess() {

		if (removedId != null) {
			joinTransaction();
			Messagess messagesss = getEntityManager().find(Messagess.class,
					Integer.parseInt(removedId));
			getEntityManager().remove(messagesss);
			getEntityManager().flush();
			this.refresh();
		}
	}
   
   //更新认领状态
	@Transactional
	public void updateStatus() {
		joinTransaction();
		Messagess messagesses = getEntityManager().find(Messagess.class,
				Integer.parseInt(removedId));
		messagesses.setStatus(status);
		getEntityManager().flush();
	}

	//记录用户点击的消息次数(消息发布者浏览本人消息不计数)
	@Transactional
	public void updateCount() {

		joinTransaction();  //添加事务


		
	//	 UpdateCountUser updateCountUser=new UpdateCountUser();//add updateCountUser constructor
	//	if (updateCountUser.makeId() == null) {
		
			//如果登陆用户的Id为空或者登陆用户的Id不等于消息发布者的id，则阅读数加1；
			int countTemp =1;
		Messagess messagesses = getEntityManager().find(Messagess.class,Integer.parseInt(removedId));
		int sum = messagesses.getCount()+countTemp;
		messagesses.setCount(sum);
		getEntityManager().flush();
	//	}
      /*  if (updateCountUser.makeId().getId() != messageWithUserId) {
			//如果登陆用户的Id为空或者登陆用户的Id不等于消息发布者的id，则阅读数加1；
			int countTemp =1;
		Messagess messagesses = getEntityManager().find(Messagess.class,Integer.parseInt(removedId));
		int sum = messagesses.getCount()+countTemp;
		messagesses.setCount(sum);
		getEntityManager().flush();
		}
       
		if (updateCountUser.makeId().getId() == messageWithUserId) {
			//如果登陆用户的Id为空或者登陆用户的Id不等于消息发布者的id，则阅读数加0；
			int countTemp =0;
		Messagess messagesses = getEntityManager().find(Messagess.class,Integer.parseInt(removedId));
		int sum = messagesses.getCount()+countTemp;
		messagesses.setCount(sum);
		getEntityManager().flush();
		}*/

		
	}

	// 遍历用户消息，去掉重复的结果
	public List<Messagess> findMessagess() {
		List<Messagess> list01 = new ArrayList<Messagess>();
		List<Messagess> list = getEntityManager().createQuery(
				"select m from Messagess m").getResultList();
		String name = withUserName;
		for (Messagess m : list) {
			String str1 = m.getText();
			if (str1.contains(name)) {
				list01.add(m);
			}
		}
		// return list01;
		Set set = new HashSet();
		List newList = new ArrayList();
		Iterator<Messagess> iter = list01.iterator();
		while (iter.hasNext()) {
			Messagess me = iter.next();
			if (set.add(me))
				newList.add(me);
		}
		return newList;
	}

	// public Date createDate() {
	// Date createSendDate = new Date();
	// return createSendDate;
	// }


	//记录用户点击的消息次数



	/**
	 * @return the removedId
	 */
	public String getRemovedId() {
		return removedId;
	}

	/**
	 * @param removedId
	 *            the removedId to set
	 */
	public void setRemovedId(String removedId) {
		this.removedId = removedId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public String getWithUserName() {
		return withUserName;
	}

	public void setWithUserName(String withUserName) {
		this.withUserName = withUserName;
	}

	public Date getTimeBegin() {
		return timeBegin;
	}

	public void setTimeBegin(Date timeBegin) {
		this.timeBegin = timeBegin;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Integer getMessageWithUserId(){
		return messageWithUserId;
	}

	public void setMessageWithUserId(Integer messageWithUserId){

		this.messageWithUserId=messageWithUserId;
	}

}
