
package com.sds.project0319.notice;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

//�������̶�? ���� ���������� ����� �� �ִ� Ŭ����
public class ListServlet extends HttpServlet{

	//�����ֱ� �޼��� ��, �ʱ�ȭ �޼��带 �̿��Ͽ�, web.xml���� �ѱ� �Ķ����������
	//�����غ���
	String user;

	public void init(ServletConfig config){
		user = config.getInitParameter("user");
		//tomcat�� �ܼ�(�͹̳�)�� �������, log���� ��¿��� ��µȴ�.
		System.out.print("�ʱ� �Ķ���� ���� "+user);
	}

	//��� ��û�� ó���ϴ� �������̹Ƿ�, get����� ��û�� ó��
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//�Խ��� ��� ��û�̹Ƿ�, ����Ŭ�� ��������
		
		/*
		JSP�� ������ �Ѵ� �������� �����ϴ�. �� ���������� ����� �� �ִ� ����̴�.
		������, �������� ���� Ŭ������ �ڵ带 �ۼ��ϴ� ����, �������� ǥ���Ҷ� ��� �͵��� ���ڿ� ó���� �ؾ�
		�ϹǷ�, ȿ�Ｚ�� �ſ� ��������
		�ذ�å) jsp�� ������ �� ���� ������ ��������� ���� �Ǵ� ������ ������(view)�� ���� �ڵ���
		   		 jsp �켼�ϰ�, �װ� �ƴ϶�� ���������� ó���Ѵ�.
		*/

		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head>");
		out.print("<meta charset=\"UTF-8\">");
		out.print("</head>");
		out.print("<body>");
		out.print("<table width=\"100%\" border=\"1px\">");
		out.print("<tr>");
		out.print("<td>No</td>");
		out.print("<td>title</td>");
		out.print("<td>writer</td>");
		out.print("<td>content</td>");
		out.print("</tr>");
		out.print("</table>");
		out.print("�ʱ�ȭ �Ķ���� ����"+user);
		out.print("</body>");
		out.print("</html>");

	}
}