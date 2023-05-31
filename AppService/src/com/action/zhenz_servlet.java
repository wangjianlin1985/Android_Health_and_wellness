package com.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.dao.DB;

import com.orm.TZhenz;


public class zhenz_servlet extends HttpServlet
{ 
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
		String type=req.getParameter("type");
		
		if(type.endsWith("zhenzAdd"))
		{
			zhenzAdd(req, res);
		}
		if(type.endsWith("zhenzMana"))
		{
			zhenzMana(req, res);
		}
		if(type.endsWith("zhenzDel"))
		{
			zhenzDel(req, res);
		}
                if(type.endsWith("zhenzEdit"))
		{
			zhenzEdit(req, res);
		}
               if(type.endsWith("zhenzSearch"))
		{
			zhenzSearch(req, res);
		}
               if(type.endsWith("zhenzAll"))
		{
			zhenzAll(req, res);
		}
		
	}
	
	
	
	
	public void zhenzAdd(HttpServletRequest req,HttpServletResponse res)
	{

		
		String zhengzhuang=req.getParameter("zhengzhuang");
               
		
		
		String sql="insert into t_zhenz(zhengzhuang) values(?)";
		Object[] params={zhengzhuang};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		
		
		mydb.closed();
		req.setAttribute("message", "添加成功！");
		req.setAttribute("path", "zhenz?type=zhenzMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	//缁撴潫
	public void zhenzDel(HttpServletRequest req,HttpServletResponse res)
	{
		int id=Integer.parseInt(req.getParameter("id"));
		
		String sql="delete from t_zhenz  where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "删除成功！");
		req.setAttribute("path", "zhenz?type=zhenzMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	public void zhenzMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List zhenzList=new ArrayList();
		String sql="select * from t_zhenz ";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TZhenz zhenz=new TZhenz();
				zhenz.setId(rs.getInt("id"));//0
				zhenz.setZhengzhuang(rs.getString("zhengzhuang"));//1
				
				
				
				zhenzList.add(zhenz);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("zhenzList", zhenzList);
		req.getRequestDispatcher("zhenz/zhenzMana.jsp").forward(req, res);
	}

public void zhenzAll(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List zhenzList=new ArrayList();
		String sql="select * from t_zhenz where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TZhenz zhenz=new TZhenz();
				zhenz.setId(rs.getInt("id"));//0
				zhenz.setZhengzhuang(rs.getString("zhengzhuang"));//1
				
				
				zhenzList.add(zhenz);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("zhenzList", zhenzList);
		req.getRequestDispatcher("qiantai/zhenz/zhenzAll.jsp").forward(req, res);
	}
	
	
	
	
	public void zhenzEdit(HttpServletRequest req,HttpServletResponse res)
	{
		int id=Integer.parseInt(req.getParameter("id"));
		String zhengzhuang=req.getParameter("zhengzhuang");
               
		
		String sql="update t_zhenz set zhengzhuang=? where id="+id;
	
                Object[] params={zhengzhuang};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "修改成功");
		req.setAttribute("path", "zhenz?type=zhenzMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	
	public void zhenzSearch(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List zhenzList=new ArrayList();
		int cailiaoId=Integer.parseInt(req.getParameter("cailiaoId"));
                 String zhengzhuang=req.getParameter("zhengzhuang");
		String shijianSta=req.getParameter("shijianSta");
		String shijianEnd=req.getParameter("shijianEnd");
		StringBuffer sql=new StringBuffer("select * from t_zhenz where del='no'");

                if(zhengzhuang!=null && !zhengzhuang.equals(""))
		{
			sql.append(" and zhengzhuang='"+zhengzhuang+"'");
		}


		if(cailiaoId!=0)
		{
			sql.append(" and cailiaoId="+cailiaoId);
		}
		if(shijianSta!=null && !shijianSta.equals(""))
		{
			sql.append(" and shijian>'"+shijianSta+"'");
		}
		if(shijianEnd!=null && !shijianEnd.equals(""))
		{
			sql.append(" and shijian<'"+shijianEnd+"'");
		}
		System.out.println(sql.toString()+"&&&&&&&&");
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql.toString(), params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TZhenz zhenz=new TZhenz();
				zhenz.setId(rs.getInt("id"));//0
				zhenz.setZhengzhuang(rs.getString("zhengzhuang"));//1
				
			
				zhenzList.add(zhenz);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("zhenzList", zhenzList);
		req.getRequestDispatcher("zhenz/zhenzMana.jsp").forward(req, res);
	}
	
	
	
	
	
	
	
	
	public void dispatch(String targetURI,HttpServletRequest request,HttpServletResponse response) 
	{
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(targetURI);
		try 
		{
		    dispatch.forward(request, response);
		    return;
		} 
		catch (ServletException e) 
		{
                    e.printStackTrace();
		} 
		catch (IOException e) 
		{
			
		    e.printStackTrace();
		}
	}
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);
	}
	
	public void destroy() 
	{
		
	}
}
