package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bean.collects;
import com.bean.comments;
import com.bean.xinxi;
import com.bean.shops;
import com.bean.types;
import com.bean.users;
import com.bean.TZhenz;

@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
public class ServletService extends HttpServlet {

	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	java.text.SimpleDateFormat formatdate = new java.text.SimpleDateFormat(
			"yyyy-MM-dd");
	java.util.Date currentTime = new java.util.Date();// 得到当前系统时间

	private Session session = null;
	private HttpServletRequest request;

	public ServletService() {
		super();
		session = HibernateSessionFactory.getSession();
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.request = request;
		session = HibernateSessionFactory.getSession();
		session.flush();
		session.clear();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("Action");
		System.out.println(action);
		String write = "";
		String sqlString = "";
		// 登录验证
		if (action.equals("login")) {
			write = login();

		}
		if (action.equals("getOneRow")) {
			write = getOneRow();
		}
		if (action.equals("Del")) {
			write = Del();
		}

		// 管理员登录验证
		if (action.equals("adminlogin")) {
			write = adminlogin();
		}
		if (action.equals("getxinxilist")) {
			write = getxinxilist();

		}
		if (action.equals("getxinxifidlist")) {
			write = getxinxifidlist();

		}
		if (action.equals("getshopslist")) {
			write = getshopslist();
		}
		if (action.equals("getComment")) {
			write = getComment();

		}
		if (action.equals("getcollectlist")) {
			write = getcollectlist();

		}
		
		if (action.equals("collect")) {
			write = collect();
		}
		if (action.equals("edit")) {
			write = edit();
		}
		if (action.equals("edituser")) {
			write = edituser();
		}
		if (action.equals("editshop")) {
			write = editshop();
		}
		
		if (action.equals("edittype")) {
			write = edittype();
		}
		if (action.equals("getnoticelist")) {
			
			sqlString = "select * from t_zhenz where 1=1 ";
			
			ResultSet rs = HibernateSessionFactory.queryBySql(sqlString);
			List list = HibernateSessionFactory.convertList(rs);
			if (list.size() > 0) {
				JSONArray json = JSONArray.fromObject(list);
				write = json.toString();
			}
		
//			List<TZhenz> list = session.createSQLQuery(sqlString)
//					.addEntity(TZhenz.class).list();
//			if (list.size() > 0) {
//				JSONArray json = JSONArray.fromObject(list);
//				write = json.toString();
//			}
		}
		System.out.println(write);
		out.println(write);
		out.flush();
		out.close();

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = HibernateSessionFactory.getSession();
		session.flush();
		session.clear();
		this.request = request;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("Action");
		String write = "";
		// 注册
		if (action.equals("register")) {
			System.out.println(request.getParameter("id"));
			users model = new users();
			if (request.getParameter("id") == null
					|| request.getParameter("id").equals("0")) {
				model = new users();

			} else {
				model = (users) (session.createQuery(
						" from users where id=" + request.getParameter("id"))
						.list().get(0));
			}
			model.setLoginid(request.getParameter("loginid"));
			model.setPasswords(request.getParameter("password"));
			model.setName(getChinese(request.getParameter("name")));

			Transaction tran = session.beginTransaction();
			session.save(model);
			tran.commit();
			write = "1";
		}
		if (action.equals("updatePwd")) {
			write = updatePwd();
		}
		if (action.equals("createcomment")) {
			write = createcomment();
		}

		out.println(write);
		out.flush();
		out.close();
	}

	private String login() {
		String write = "";
		String loginid = request.getParameter("loginid");
		String passwords = request.getParameter("passwords");

		List<users> list = session.createQuery(
				" from users where loginid='" + loginid + "' and passwords='"
						+ passwords + "'").list();
		if (list.size() > 0) {
			write = JSONArray.fromObject(list.get(0)).toString();
		}
		return write;
	}

	private String adminlogin() {
		String write = "";
		String loginid = request.getParameter("loginid");
		String passwords = request.getParameter("passwords");
		List<users> list = session.createQuery(
				" from admins where loginid='" + loginid + "' and passwords='"
						+ passwords + "'").list();
		if (list.size() > 0) {
			write = "1";
		} else {
			write = "0";
		}
		return write;
	}

	

	private String getxinxilist() {
		String write = "";
		String sqlString = "from xinxi where 1=1 ";
		if (request.getParameter("typeid") != null) {
			sqlString += " and typeid=" + request.getParameter("typeid");
		}
		sqlString += " order by id desc";
		List list = session.createQuery(sqlString).list();
		if (list.size() > 0) {
			JSONArray json = JSONArray.fromObject(list);
			write = json.toString();
		}
		return write;
	}
	
	private String getxinxifidlist() {
		String write = "";
		String sqlString = "from xinxi where 1=1 ";
		if (request.getParameter("typeid") != null) {
			sqlString += " and fid=" + request.getParameter("typeid");
		}
		sqlString += " order by id desc";
		System.out.println("sqlString="+sqlString);
		List list = session.createQuery(sqlString).list();
		if (list.size() > 0) {
			JSONArray json = JSONArray.fromObject(list);
			write = json.toString();
		}
		return write;
	}

	private String getComment() {
		String write = "";
		String sqlString = "from comments where 1=1 ";
		if (request.getParameter("shopid") != null) {
			sqlString += " and shopid =" + request.getParameter("shopid");
		}
		sqlString += " order by id desc";
		List list = session.createQuery(sqlString).list();
		if (list.size() > 0) {
			JSONArray json = JSONArray.fromObject(list);
			write = json.toString();
		}
		return write;
	}
	private String getcollectlist() {
		String write = "";
		String sqlString = "select collects.id,users.loginid,users.`name` username,collects.createtime,shops.name,shops.img_url,shops.id shopid from collects inner join shops on collects.shopid=shops.id inner JOIN users ON users.id=collects.userid ";
		if (request.getParameter("userid") != null) {
			sqlString += " and userid =" + request.getParameter("userid");
		}
		ResultSet rs=HibernateSessionFactory.queryBySql(sqlString);
		List list = HibernateSessionFactory.convertList(rs);
		if (list.size() > 0) {
			JSONArray json = JSONArray.fromObject(list);
			write = json.toString();
		}
		return write;
	}
	
	private String getshopslist() {
		String write = "";
		String sqlString = "from shops where 1=1 ";
		if (request.getParameter("typeid") != null) {
			sqlString += " and typeid=" + request.getParameter("typeid");
		}
		sqlString += " order by id desc";
		List list = session.createQuery(sqlString).list();
		if (list.size() > 0) {
			JSONArray json = JSONArray.fromObject(list);
			write = json.toString();
		}
		return write;
	}

	private String edit() throws UnsupportedEncodingException {
		int id = Integer.valueOf((request.getParameter("ID")));
		xinxi model;
		if (id == 0) {
			model = new xinxi();

		} else {
			model = (xinxi) (session
					.createQuery(" from xinxi where id=" + id).list().get(0));
		}

		if (request.getParameter("img_url") != null
				&& request.getParameter("img_url").length() > 0) {
			model.setImg_url(request.getParameter("img_url"));
		}

		model.setIntro(getChinese(request.getParameter("intro")));
		model.setTitle(getChinese(request.getParameter("title")));
		model.setPractice(getChinese(request.getParameter("practice")));
		model.setTypeid(Integer.valueOf(request.getParameter("typeid")));
		model.setTypename(getChinese(request.getParameter("typename")));
		Transaction tran = session.beginTransaction();

		if (id != 0) {
			session.update(model);
		} else {
			session.save(model);
		}
		tran.commit();
		return "1";
	}
	private String editshop() throws UnsupportedEncodingException {
		int id = Integer.valueOf((request.getParameter("ID")));
		shops model;
		if (id == 0) {
			model = new shops();

		} else {
			model = (shops) (session
					.createQuery(" from shops where id=" + id).list().get(0));
		}

		if (request.getParameter("img_url") != null
				&& request.getParameter("img_url").length() > 0) {
			model.setImg_url(request.getParameter("img_url"));
		}

		model.setSpecialty(getChinese(request.getParameter("specialty")));
		model.setName(getChinese(request.getParameter("name")));
		model.setTypeid(Integer.valueOf(request.getParameter("typeid")));
		model.setLat(request.getParameter("lat"));
		model.setLon(request.getParameter("lon"));
		Transaction tran = session.beginTransaction();

		if (id != 0) {
			session.update(model);
		} else {
			session.save(model);
		}
		tran.commit();
		return "1";
	}
	
	private String edituser() throws UnsupportedEncodingException {
		int id = Integer.valueOf((request.getParameter("ID")));
		users model;
		if (id == 0) {
			model = new users();

		} else {
			model = (users) (session.createQuery(" from users where id=" + id)
					.list().get(0));
		}

		model.setLoginid(getChinese(request.getParameter("loginid")));
		model.setName(getChinese(request.getParameter("name")));
		model.setPasswords(request.getParameter("passwords"));
		Transaction tran = session.beginTransaction();
		if (id != 0) {
			session.update(model);
		} else {
			session.save(model);
		}
		tran.commit();
		return "1";
	}

	private String edittype() throws UnsupportedEncodingException {
		int id = Integer.valueOf((request.getParameter("ID")));
		types model;
		if (id == 0) {
			model = new types();

		} else {
			model = (types) (session.createQuery(" from types where id=" + id)
					.list().get(0));
		}

		model.setTypename(getChinese(request.getParameter("typename")));

		Transaction tran = session.beginTransaction();
		if (id != 0) {
			session.update(model);
		} else {
			session.save(model);
		}
		tran.commit();
		return "1";
	}

	private String createcomment() {
		comments model = new comments();
		model.setBody(getChinese(request.getParameter("body")));
		model.setCreatetime(formatter.format(currentTime));
		model.setShopid(Integer.valueOf(request.getParameter("shopid")));
		model.setUsername(request.getParameter("username"));
		model.setUserid(Integer.valueOf(request.getParameter("userid")));
		Transaction tran = session.beginTransaction();
		session.save(model);
		session.flush();
		tran.commit();
		return "1";
	}
	// 收藏
	private String collect() throws UnsupportedEncodingException {
		collects model;
		List list = session.createQuery(
				" from collects where shopid="
						+ request.getParameter("shopid") + " and userid="
						+ request.getParameter("userid")).list();
		if (list.size() == 0) {
			model = new collects();
			model.setCreatetime(formatdate.format(currentTime));
		} else {
			return "-1";// 已经收藏
		}

		model.setUserid(Integer.valueOf(request.getParameter("userid")));
		model.setShopid(Integer.valueOf(request.getParameter("shopid")));
		Transaction tran = session.beginTransaction();
		session.save(model);
		tran.commit();
		return "1";
	}
	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String updatePwd() throws UnsupportedEncodingException {
		List list = session.createQuery(
				" from users where loginid='" + request.getParameter("loginid")
						+ "' and passwords='"
						+ request.getParameter("passwords") + "'").list();
		if (list.size() == 0) {
			return "-1";// 账号或密码错误
		} else {
			users model = (users) list.get(0);
			model.setPasswords(request.getParameter("passwords_new"));
			Transaction tran = session.beginTransaction();
			session.save(model);
			tran.commit();
			return "1";// 修改成功
		}

	}

	/**
	 * 取得中文
	 * 
	 * @param 原字符
	 * @return
	 */
	private String getChinese(String str) {
		if (str == null) {
			return "";
		}
		try {
			return URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";

		}
	}

	/**
	 * 公用的获取一行数据方法
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getOneRow() throws UnsupportedEncodingException {
		List list = null;
		if (request.getParameter("ID") == null) {
			list = session
					.createQuery(" from " + request.getParameter("Table"))
					.list();
		} else {
			list = session.createQuery(
					" from " + request.getParameter("Table") + " where id="
							+ request.getParameter("ID")).list();
		}

		JSONArray json = JSONArray.fromObject(list);
		return json.toString();
	}

	public String Del() {
		int ID = Integer.valueOf(request.getParameter("ID"));
		String Table = request.getParameter("Table");
		String PK_Name = "id";
		String sql = "delete from " + Table + " where " + PK_Name + "=" + ID;
		HibernateSessionFactory.updateExecute(sql);
		return "1";

	}

	@Override
	public void init() throws ServletException {

	}

}
