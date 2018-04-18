<%--
  Created by IntelliJ IDEA.
  User: Ferdi_S672
  Date: 4/17/2018
  Time: 9:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:genericpage>
    <jsp:attribute name="title">
        List Member
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">List Members</h1>
            </div>
            <!-- /.col-lg-12 -->

        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Kitchen Sink
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-sm-6">
                                <a href="MemberController?action=insert">
                                    <button type="button" class="btn btn-primary">Add Member</button>
                                </a>
                                <p></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>File</th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="member" items="${members}">
                                            <tr>
                                                <td>${tes}<c:out value="${member.id}" /></td>
                                                <td><c:out value="${member.name}" /></td>
                                                <td>
                                                    <c:if test = "${not empty member.filename}">
                                                        <a href="FileController?action=download&userId=<c:out value="${member.id}" />">
                                                            <button type="button" class="btn btn-default btn-circle">
                                                                <i class="fa fa-edit"></i>
                                                            </button>
                                                            Download
                                                        </a>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <a href="MemberController?action=edit&userId=<c:out value="${member.id}" />">
                                                        <button type="button" class="btn btn-default btn-circle">
                                                            <i class="fa fa-edit"></i>
                                                        </button>
                                                        Edit

                                                    </a>
                                                </td>
                                                <td>
                                                    <a href="MemberController?action=delete&userId=<c:out value="${member.id}" />">
                                                        <button type="button" class="btn btn-danger btn-circle">
                                                            <i class="fa fa-times"></i>
                                                        </button>
                                                        Delete
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- /.table-responsive -->
                            </div>
                        </div>

                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-6 -->
        </div>
    </jsp:body>
</t:genericpage>