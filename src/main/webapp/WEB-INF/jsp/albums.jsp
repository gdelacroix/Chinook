<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

            <html>

            <head>
                <meta charset="UTF-8" />
                <title>Liste des Albums</title>
                <link href="/webjars/bootstrap/5.2.0/css/bootstrap.min.css" rel="stylesheet" />
            </head>

            <body id="page-top">
                <!-- Navigation -->
                <!-- Navigation-->

                <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
                    <div class="container px-4">
                        <a class="navbar-brand" href="#page-top">Start Bootstrap</a>

                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                            aria-label="Toggle navigation">
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
                                    <a class="nav-link" href="/artists">Artistes</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="/albums">Albums</a>
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
                <section class="container mt-5 pt-5">
                    <h2>Liste des Albums</h2>
                    <div class="text-end mb-3">
                        <a class="btn btn-primary" href="/albums/add">Ajouter un Album</a>
                    </div>
                    <table class="table table-bordered table-striped">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Titre</th>
                                <th>Artiste</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="album" items="${albums}">
                                <tr>
                                    <td>${album.id}</td>
                                    <td>${album.title}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty album.artist}">
                                                ${album.artist.name}
                                            </c:when>
                                            <c:otherwise>
                                                -
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="/albums/update?id=${album.id}"
                                            class="btn btn-warning btn-sm">Modifier</a>
                                        <a href="/albums/delete?id=${album.id}" class="btn btn-danger btn-sm"
                                            onclick="return confirm('Voulez-vous vraiment supprimer cet album ?');">Supprimer</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </section>

                <script src="/webjars/jquery/3.6.1/jquery.min.js"></script>
                <script src="/webjars/bootstrap/5.2.0/js/bootstrap.min.js"></script>
            </body>

            </html>