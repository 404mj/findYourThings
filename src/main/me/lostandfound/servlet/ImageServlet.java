package me.lostandfound.servlet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class ImageServlet extends HttpServlet {
	private Integer usersId;
	private Integer messagessId;

	private byte[] imageByte;
	private byte[] imageByteU;

	/**
	 * Get data source by JNDI lookup.
	 * 
	 * @return
	 * @throws NamingException
	 */
	private DataSource getDS() throws NamingException {
		Context context = new InitialContext();
		return (DataSource) context.lookup("findyourthingsDatasource");
	}

	/**
	 * 获得用户的图片.
	 */
	private void getImageByte() {
		DataSource ds = null;
		Connection conn = null;
		try {
			ds = getDS();
			conn = ds.getConnection();
			Statement stat = conn.createStatement();
			ResultSet res = stat.executeQuery("select * from users where id='"
					+ usersId + "'");

			while (res.next()) {
				imageByte = res.getBytes("user_image");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (NamingException ex) {
			ex.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

	}

	/**
	 * 得到物品的图片
	 */
	private void getFinanceImageByte() {
		DataSource ds = null;
		Connection conn = null;
		// System.out.println("getFinanceImageByte() 调用");
		try {
			ds = getDS();
			conn = ds.getConnection();
			Statement stat = conn.createStatement();
			ResultSet res = stat
					.executeQuery("select * from messagess where id='"
							+ messagessId + "'");

			while (res.next()) {
				imageByte = res.getBytes("image");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (NamingException ex) {
			ex.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String parm;
		String financeParm;
		synchronized (this) {
			// System.out.println("Servlet 调用");
			resp.setContentType("image/jpeg");
			resp.setHeader("Pragma", "No-cache");
			resp.setHeader("Cache-Control", "no-cache");
			resp.setDateHeader("Expires", 0);
			parm = req.getParameter("usersId");
			financeParm = req.getParameter("messagessId");
			if (parm != null) {
				usersId = Integer.parseInt(parm);
				getImageByte();
			} else if (financeParm != null) {
				// System.out.println("qqqqqqqqqid是" + financeParm);
				messagessId = Integer.parseInt(financeParm);

				getFinanceImageByte();
			}

			resp.getOutputStream().write(imageByte, 0, imageByte.length);
			// resp.getOutputStream().write(imageByteU, 0, imageByteU.length);

		}
		// resp.getOutputStream().write(imageByteU, 1, imageByteU.length);
	}
}
