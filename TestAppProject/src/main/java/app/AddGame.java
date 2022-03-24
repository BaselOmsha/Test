package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import model.Game;

@WebServlet(name = "AddGame", urlPatterns = { "/addgame" })

public class AddGame extends HttpServlet {
	/*
	 * If the request type is POST, the request is transferred to the method doGet
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		/*
		 * With a RequestDispatcher object is the htmlstart.html file included to this
		 * servlet
		 */
		RequestDispatcher rd = request.getRequestDispatcher("staticpages/htmlstart.html");
		rd.include(request, response);
		;

		/*
		 * in this example is the servlet's code shown only partly.
		 * 
		 * See the previous example: The code below should replace the code between
		 * include statements of the servlet's doPost method.
		 * 
		 * The statements below:
		 * 
		 * readGame - calling readGame method of the servlet. Parameter request contains
		 * all the information coming from the html page addgame.html. See the method
		 * readGame later below new Dao() - creating a new Dao object saveGame - calls
		 * the method saveGame of the Dao object passing the previously created Game
		 * object to the method. readAllGame - reading all the game objects from the
		 * database printGameList - passing the ArrayList of Game objects to the method
		 * printAllGame, which prints Game objects close - closing the database
		 * connection
		 */
		// Read parameters to Model
		Game game = readGame(request);

		// Create connection
		Dao dao = new Dao();

		// Save value and query total list
		dao.saveGame(game);
		ArrayList<Game> list = dao.readAllGame();

		// print output and close connection
		printGameList(out, list);
		dao.close();

		out.println("<br><a href='./form.html'>Back to form</a>");

		/*
		 * With a RequestDispatcher object is the htmlend.html file included to this
		 * servlet
		 */
		rd = request.getRequestDispatcher("staticpages/htmlend.html");
		rd.include(request, response);
		;
	}

	/*
	 * The method readGame of the servlet class.
	 * 
	 * The method gets the request object as a parameter. The method creates a new
	 * Game object and sets the values of the request parameters breed and weight to
	 * the Game object.
	 */
	private Game readGame(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Game game = new Game();
		game.setBreed(request.getParameter("breed"));
		game.setWeight(request.getParameter("weight"));
		return game;
	}

	/*
	 * The method printGameList of the servlet class.
	 * 
	 * This method prints all the game objects in the ArrayList as an UL element.
	 * Printing uses the toString method of the class Game, which happends when
	 * there is such a method and printing an object.
	 */
	private void printGameList(PrintWriter out, ArrayList<Game> list) {
		// TODO Auto-generated method stub
		out.println("<ul>");
		for (Game g : list) {
			out.println("<li>" + g);
		}
		out.println("</ul>");
	}

}
