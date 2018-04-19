package id.ferdi.training.controller;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import id.ferdi.training.dao.MemberDAO;
import id.ferdi.training.model.Member;
import id.ferdi.training.util.FileUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

@WebServlet(name = "MemberController", urlPatterns = {"/MemberController"})
@MultipartConfig
public class MemberController extends HttpServlet {
    private static String INSERT_OR_EDIT = "/member.jsp";
    private static String LIST_USER = "/listMember.jsp";
    
    private MemberDAO dao;

    public MemberController() {
        super();
        dao = new MemberDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        if ( name != null && name.isEmpty() ) {
            RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
            request.setAttribute("message",new String("Name cannot be empty."));
            view.forward(request, response);
        }
        else {
            Member member = new Member();
            member.setName(request.getParameter("name"));
            String userid = request.getParameter("userid");

            //file store
            Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">

            if (filePart.getSize() != 0 && !filePart.getName().equals("")) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                InputStream fileContent = filePart.getInputStream();

                FileUtil.saveFile(fileName,fileContent);

                //file saved to PostgreSQL
                member.setFile(fileContent);
                member.setFilename(fileName);
            }
            else {
                member.setFile(null);
                member.setFilename(null);
            }



            if(userid == null || userid.isEmpty()) {
                dao.create(member);
            }
            else {
                member.setId(Integer.parseInt(userid));
                dao.update(member);
            }

            RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
            request.setAttribute("members", dao.getAllMembers());
            view.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward;
        String action;
        // Check if username parameter exists
        if (request.getParameterMap().containsKey("action")) {
            action = request.getParameter("action");
        }
        else {
            action = "listMember";
        }


        if (action.equalsIgnoreCase("delete")){
            int userId = Integer.parseInt(request.getParameter("userId"));
            dao.delete(userId);
            forward = LIST_USER;
            request.setAttribute("members", dao.getAllMembers());
        }
        else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("userId"));
            Member member = dao.read(userId);
            request.setAttribute("member", member);
        }
        else if (action.equalsIgnoreCase("listMember")){
            forward = LIST_USER;
            List<Member> data = dao.getAllMembers();
            request.setAttribute("members", data);
        }
        else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
}