package id.ferdi.training.controller;

import id.ferdi.training.dao.MemberDAO;
import id.ferdi.training.model.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@WebServlet(name = "FileController", urlPatterns = {"/FileController"})
public class FileController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberDAO dao = new MemberDAO();

        Member member;
        member = dao.read(Integer.parseInt(request.getParameter("userId")));

        response.setHeader("Content-disposition", "attachment; filename="+ member.getFilename());
        response.setContentType("application/octet-stream");

        //response.getOutputStream().write(member.getFile().readAllBytes()); //leave this as alternative

        ByteArrayOutputStream byteArrayOutputStreamStream = new ByteArrayOutputStream();
        member.getFile().transferTo(byteArrayOutputStreamStream);
        response.getOutputStream().write(byteArrayOutputStreamStream.toByteArray());

    }
}
