package com.infoshareacademy.searchengine.servlets;

import com.infoshareacademy.searchengine.dao.UserRepositoryDaoBean;
import com.infoshareacademy.searchengine.dao.UsersRepositoryDao;
import com.infoshareacademy.searchengine.domain.User;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("find-user-by-id")
public class FindUserByIdServlet extends HttpServlet {
    @Inject
    private UsersRepositoryDao userRepositoryDaoBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if(req.getParameter("id")==null){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        User user = userRepositoryDaoBean.getUserById(Integer.valueOf(req.getParameter("id")));
            if(user==null){
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return ;
            }
                PrintWriter writer = resp.getWriter();
                writer.println(user.getName());



    }
}
