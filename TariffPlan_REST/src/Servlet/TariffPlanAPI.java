package Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Resources.TariffPlan;

/**
 * Servlet implementation class TariffPlanAPI
 */
@WebServlet("/TariffPlanAPI")
public class TariffPlanAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TariffPlan Tplan = new TariffPlan();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TariffPlanAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String output = Tplan.CreateTariffPlan(request.getParameter("tariffPlanId"),
				request.getParameter("tariffBlock"), request.getParameter("unitRate"),
				request.getParameter("fixedCharge"));
		response.getWriter().write(output);
	}

	private Map getParasMap(HttpServletRequest request) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {

				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}



	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = Tplan.DeleteTariffPlan(paras.get("TariffID").toString());
		response.getWriter().write(output);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = Tplan.UpdateTariffPlan(paras.get("tariffPlanId").toString(),
				paras.get("tariffBlock").toString(), paras.get("unitRate").toString(),
				paras.get("fixedCharge").toString());
		response.getWriter().write(output);

	}


}
