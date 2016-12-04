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
	
	
	// �����ɾ������
		@Transactional
		public void removedComents() {

			if (removedId != null) {

				// �������

				joinTransaction();

				// ��ȡ��Ӧ��IDʵ��

				Comment comments = getEntityManager().

				find(Comment.class, Integer.parseInt(removedId));

				// ɾ����Ӧ��ʵ��

				getEntityManager().remove(comments);

				// ִ��ɾ�� ���߸��� ���߳־û� ʱִ��flush ˢ�µ����ݿ�

				getEntityManager().flush();

				// ����ˢ�����ݿ��е�ǰ̨����

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
