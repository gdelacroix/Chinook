<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
                <!DOCTYPE html>
                <html lang="fr">

                <head>
                    <meta charset="utf-8" />
                    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                    <meta name="description" content="" />
                    <meta name="author" content="" />
                    <title>Formulaire Album</title>
                    <link rel="icon" type="image/x-icon" href="/assets/favicon.ico" />
                    <!-- Core theme CSS (includes Bootstrap)-->
                    <link href="/webjars/bootstrap/5.2.0/css/bootstrap.min.css" rel="stylesheet" />
                    <link href="/css/styles.css" rel="stylesheet" />
                </head>

                <body id="page-top">
                    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
                        <div class="container px-4">
                            <a class="navbar-brand" href="#page-top">Start Bootstrap</a>
                            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
                                aria-expanded="false" aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"> </span>
                            </button>
                            <div class="collapse navbar-collapse" id="navbarResponsive">
                                <ul class="navbar-nav ms-auto">
                                    <li class="nav-item">
                                        <a class="nav-link" href="/employees">Employés</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="/customers">Clients</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="/albums">Albums</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="/artists">Artistes</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#services">Services</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#contact">Contact</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </nav>

                    <section>
                        <div class="container px-4">
                            <div class="row gx-4 justify-content-center">
                                <div class="col-lg-8">
                                    <h2>${title}</h2>
                                    <form:form method="post" modelAttribute="album">
                                        <form:hidden path="id" />

                                        <fieldset class="form-group">
                                            <div class="form-floating mb-3">
                                                <form:input path="title" type="text" class="form-control"
                                                    required="required" />
                                                <form:label path="title">Titre de l'Album</form:label>
                                            </div>
                                            <form:errors path="title" cssClass="text-warning" />
                                        </fieldset>

                                        <!-- Sélection de l'Artiste -->
                                        <fieldset class="form-group">
                                            <div class="form-floating mb-3">
                                                <form:select path="artist" class="form-control">
                                                    <form:option value="">-- Sélectionner un artiste --</form:option>
                                                    <c:forEach var="artist" items="${artists}">
                                                        <form:option value="${artist.id}">
                                                            ${artist.name}
                                                        </form:option>
                                                    </c:forEach>
                                                </form:select>
                                                <form:label path="artist">Artiste</form:label>
                                            </div>
                                            <form:errors path="artist" cssClass="text-warning" />
                                        </fieldset>

                                        <button type="submit" class="btn btn-success">Enregistrer</button>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </section>

                    <!-- Footer-->
                    <footer class="py-5 bg-dark">
                        <div class="container px-4">
                            <p class="m-0 text-center text-white">
                                Copyright &copy; Your Website 2023
                            </p>
                        </div>
                    </footer>

                    <script src="/webjars/jquery/3.6.1/jquery.min.js"></script>
                    <script src="/webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>
                    <script src="/js/scripts.js"></script>
                </body>

                </html>