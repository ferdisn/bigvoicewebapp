<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Ferdi_S672
  Date: 4/16/2018
  Time: 8:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>




<t:genericpage>
    <jsp:attribute name="title">
        Add / Edit Member
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Add / Edit Members</h1>
            </div>
            <!-- /.col-lg-12 -->

        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Add / Edit Member
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <c:if test = "${not empty message}">
                            <div class="alert alert-danger">
                                <c:out value="${message}" />
                            </div>
                        </c:if>
                        <form method="POST" action='MemberController' name="frmAddUser" enctype="multipart/form-data">
                            <div class="form-group">
                                <label>User ID :</label>
                                <input class="form-control" type="text" readonly="readonly" name="userid"
                                       value="<c:out value="${member.id}" />" />
                            </div>
                            <div class="form-group">
                                <label for="nameField">Name : </label>
                                <input class="form-control" id="nameField"
                                       type="text" name="name"
                                       value="<c:out value="${member.name}" />" />
                            </div>
                            <div class="form-group">
                                <label for="fileField">File Attachment : </label>
                                <input type="file" name="file" id="fileField" />
                            </div>

                            <input class="btn btn-primary" type="submit" value="Submit" />
                            <a href="MemberController?action=listMember">
                                <button type="button" class="btn btn-default">Go Back</button>
                            </a>
                        </form>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-6 -->
        </div>
    </jsp:body>
</t:genericpage>