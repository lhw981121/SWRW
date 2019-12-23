<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<footer>
    <div class="footer-area pt-100 pb-100 clearfix minus-30">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-3 col-sm-6">
                    <div class="footer-widget">
                        <div class="widget-title">
                            <a href="index.html"><img src="/SWRW/public/assets/images/footer-logo.png" alt="image" /></a>
                        </div>
                        <p><fmt:message key="FooterLeft"/></p>
                        <div class="social-link mt-4">
                            <ul>
                                <li class="facebook"><a href="javascript:;"><i class="fa fa-facebook-f"></i></a></li>
                                <li class="twitter"><a href="javascript:;"><i class="fa fa-twitter"></i></a></li>
                                <li class="linkedin"><a href="javascript:;"><i class="fa fa-linkedin" ></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6">
                    <div class="footer-widget">
                        <div class="widget-title">
                            <h3><fmt:message key="Applicant"/></h3>
                        </div>
                        <div class="widget-link">
                            <ul>
                                <li><a href="javascript:;"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="BrowseJobs"/></a></li>
                                <li><a href="javascript:;"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="CompleteResume"/></a></li>
                                <li><a href="javascript:;"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="MyBookmarks"/></a></li>
                                <li><a href="javascript:;"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="JobAlerts"/></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6">
                    <div class="footer-widget">
                        <div class="widget-title">
                            <h3><fmt:message key="Company"/></h3>
                        </div>
                        <div class="widget-link">
                            <ul>
                                <li><a href="javascript:;"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="Employers"/></a></li>
                                <li><a href="javascript:;"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="AddJob"/></a></li>
										  <li><a href="javascript:;"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="JobListing"/></a></li>
                                <li><a href="javascript:;"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="EmployersGrid"/></a></li>
                                <li><a href="javascript:;"><i class="fa fa-angle-right" aria-hidden="true"></i><fmt:message key="EmployerListing"/></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6">
                    <div class="footer-widget">
                        <div class="widget-title">
                            <h3><fmt:message key="SubscribeUs"/></h3>
                        </div>
                        <div class="widget-text">
                            <form action="###">
                                <input type="email" placeholder="<fmt:message key="YourEmail"/>" />
                                <button type="button" class="buttonfx curtainup"><fmt:message key="Subscribe"/></button>
                            </form>
                            <p><fmt:message key="FooterRight"/></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer-bottom-area clearfix">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="footer-bottom text-center">
                        <p><fmt:message key="Copyright"/></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
