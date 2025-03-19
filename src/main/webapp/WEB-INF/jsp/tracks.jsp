<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta charset="UTF-8" />
    <title>Liste des Pistes</title>
    <link href="/webjars/bootstrap/5.2.0/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body id="page-top">
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
        <div class="container px-4">
            <a class="navbar-brand" href="#page-top">Start Bootstrap</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                    aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
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
                        <a class="nav-link" href="/tracks">Pistes</a>
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
        <h2>Liste des Pistes</h2>
        <div class="text-end mb-3">
            <a class="btn btn-primary" href="/tracks/add">Ajouter une Piste</a>
        </div>
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Album</th>
                    <th>Artiste</th>
                    <th>Genre</th>
                    <th>Durée</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="track" items="${tracks}">
                    <tr>
                        <td>${track.id}</td>
                        <td>${track.name}</td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty track.album}">
                                    ${track.album.title}
                                </c:when>
                                <c:otherwise>
                                    -
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty track.album and not empty track.album.artist}">
                                    ${track.album.artist.name}
                                </c:when>
                                <c:otherwise>
                                    -
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty track.genre}">
                                    ${track.genre.name}
                                </c:when>
                                <c:otherwise>
                                    -
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${track.milliseconds} ms</td>
                        <td>
                            <a href="/tracks/update?id=${track.id}" class="btn btn-warning btn-sm">Modifier</a>
                            <a href="/tracks/delete?id=${track.id}" class="btn btn-danger btn-sm"
                               onclick="return confirm('Voulez-vous vraiment supprimer cette piste ?');">Supprimer</a>
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
