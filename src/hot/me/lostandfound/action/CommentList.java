package me.lostandfound.action;

import me.lostandfound.model.*;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.framework.EntityQuery;

import java.util.Arrays;

@Name("commentList")
public class CommentList extends EntityQuery<Comment> {

	private static final String EJBQL = "select comment from Comment comment";

	private static final String[] RESTRICTIONS = {"lower(comment.commentContent) like lower(concat(#{commentList.comment.commentContent},'%'))",};
    
	private String removedId;
	
	private Comment comment = new Comment();

	public CommentList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Comment getComment() {
		return comment;
	}
	
	
	// 这个是删除方法
		@Transactional
		public void removedComents() {

			if (removedId != null) {

				// 添加事务

				joinTransaction();

				// 获取对应的ID实体

				Comment comments = getEntityManager().

				find(Comment.class, Integer.parseInt(removedId));

				// 删除对应的实体

				getEntityManager().remove(comments);

				// 执行删除 或者更新 或者持久化 时执行flush 刷新到数据库

				getEntityManager().flush();

				// 重新刷新数据库中到前台调用

				this.refresh();

			}

		}

		public String getRemovedId() {
			return removedId;
		}

		public void setRemovedId(String removedId) {
			this.removedId = removedId;
		}
}
