<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

            <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
            <html>

            <head>
            </head>
            <script src="<c:url value='/js/client/information.js'/>"></script>
            <script src="<c:url value='/js/client/password.js'/>"></script>

            <body>

                <div class="container">
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-10">
                            <br>
                            <p style="font-size: 20px">
                                <b>Thông tin tài khoản:</b>
                            </p>
                            <br>
                            <h3 style="line-height: 2;">
                                <span style="font-weight: bold">Họ tên: </span>${name}
                            </h3>
                            <h3 style="line-height: 2;">
                                <span style="font-weight: bold">Số điện thoại: </span>${phone}
                            </h3>
                            <h3 style="line-height: 2;">
                                <span style="font-weight: bold">Email: </span>${email}
                            </h3>
                            <h3 style="line-height: 2;">
                                <span style="font-weight: bold"> Địa chỉ: </span>${address}
                            </h3>
                            <br> <a class="btn btn-primary" data-toggle="modal" data-target="#modalInformation">Cập
                                nhật thông tin cá nhân</a>
                            &nbsp; &nbsp; &nbsp; <a class="btn btn-danger" data-toggle="modal"
                                data-target="#modalChangePassword">Đổi mật khẩu</a> <br> <br>
                            <br>
                            <h3>
                                <b>Lịch sử mua hàng:</b>
                            </h3>
                            <br>
                            <table class="table-cart-checkout mytable">
                                <tr>
                                    <th>Mã đơn hàng</th>
                                    <th>Ngày mua</th>
                                    <th>Ngày giao hàng</th>
                                    <th>Ngày nhận hàng</th>
                                    <th>Sản phẩm</th>
                                    <th>Tổng tiền</th>
                                    <th>Trạng thái đơn hàng</th>
                                </tr>

                                <c:forEach var="order" items="${orders}" varStatus="loop">
                                    <tr style="text-align: center;">
                                        <td>${order.id}</td>

                                        <td>
                                            <fmt:formatDate value="${order.createDate}" pattern="dd/MM/yyyy" />
                                        </td>
                                        <td>
                                            <c:if test="${order.shipDate}.equals(${order.createDate})">
                                                <fmt:formatDate value="${order.shipDate}" pattern="dd/MM/yyyy" />
                                            </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${order.deliveryDate}.equals(${order.createDate})">
                                                <fmt:formatDate value="${order.deliveryDate}" pattern="dd/MM/yyyy" />
                                            </c:if>
                                        </td>

                                        <td>
                                            <c:forEach var="product" items="${order.orderProducts}">
                                                <p>
                                                    <a
                                                        href='<%=request.getContextPath()%>/product=${product.product.id}'>${product.product.name}</a><br>
                                                </p>
                                                <p>Số lượng: ${product.quantity}</p>
                                                <hr>
                                                <c:set var="total" value="${total + product.quantity*product.price}" />
                                                <c:forEach var="gift" items="${product.product.gifts}">
                                                    <c:if test="${gift.inventory ne 0}">
                                                        <p>
                                                            ${gift.name}<br>
                                                        </p>
                                                        <hr>
                                                    </c:if> 
                                                </c:forEach>
                                            </c:forEach>
                                        </td>

                                        <td class="tongGiaTri">
                                            <fmt:formatNumber value="${order.totalPrice}" type="number"
                                                pattern="#,##0" /> VNĐ
                                        </td>
                                        <td>${order.status}</td>
                                    </tr>
                                </c:forEach>
                            </table>

                        </div>
                        <div class="col-md-1"></div>
                    </div>
                </div>
                <!-- Modal cập nhật thông tin -->
                <div class="modal fade" id="modalInformation" tabindex="-1" role="dialog"
                    aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">
                                    <b>Cập nhật thông tin tài khoản</b>
                                </h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <form action="<%=request.getContextPath()%>/api/account/updateInfo" method="post">
                                <div class="modal-body">
                                    <div class="form-group ">
                                        <label style="line-height: 2">Họ tên khách hàng*:</label><br>
                                        <label id="nameWarning" style="color: red"></label>
                                        <input class="form-control" id="name" name="name" type="text">
                                    </div>
                                    <div class="form-group ">
                                        <label style="line-height: 2">Địa chỉ email*:</label><br>
                                        <label id="emailWarning" style="color: red"></label> <input class="form-control"
                                            id="email" name="email" type="email">
                                    </div>
                                    <div class="form-group ">
                                        <label style="line-height: 2">Số điện thoại*:</label><br> <label
                                            id="phoneWarning" style="color: red"></label> <input class="form-control"
                                            id="phone" name="phone" type="text">
                                    </div>
                                    <div class="form-group">
                                        <label style="line-height: 2">Địa chỉ*:</label><br> <label id="addressWarning"
                                            style="color: red"></label>
                                        <input class="form-control" id="address" name="address" type="text">
                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                    <input type="submit" class="btn btn-primary" value="Cập
                                        nhật">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- Modal cập nhật thông tin -->

                <!-- Modal đổi pass -->

                <div class="modal fade" id="modalChangePassword" tabindex="-1" role="dialog"
                    aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">
                                    <b>Thay đổi mật khẩu</b>
                                </h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <form action="<%=request.getContextPath()%>/api/account/updatePassword" method="post">
                                <div class="modal-body">
                                    <div class="form-group ">
                                        <label style="line-height: 2">Mật khẩu cũ*:</label><br> <label id="oldWarning"
                                            style="color: red"></label> <input class="form-control" id="oldPassword"
                                            name="oldPassword" type="password">
                                    </div>
                                    <div class="form-group ">
                                        <label style="line-height: 2">Mật khẩu mới*:</label><br> <label id="new1Warning"
                                            style="color: red"></label> <input class="form-control" id="newPassword"
                                            name="newPassword" type="password">
                                    </div>
                                    <div class="form-group ">
                                        <label style="line-height: 2">Xác nhận mật khẩu mới*:</label><br>
                                        <label id="new2Warning" style="color: red"></label> <input class="form-control"
                                            id="confirmPassword" name="confirmPassword" type="password">
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                    <input type="submit" class="btn btn-primary" value="Đổi mật khẩu">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- Modal đổi pass -->


            </body>

            </html>