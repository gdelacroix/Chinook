<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%@ taglib
uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Formulaire Piste</title>
    <link rel="icon" type="image/x-icon" href="/assets/favicon.ico" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link
      href="/webjars/bootstrap/5.2.0/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <link href="/css/styles.css" rel="stylesheet" />
  </head>
  <body id="page-top">
    <nav
      class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
      id="mainNav"
    >
      <div class="container px-4">
        <a class="navbar-brand" href="#page-top">Start Bootstrap</a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarResponsive"
          aria-controls="navbarResponsive"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
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
      <div class="row justify-content-center">
        <div class="col-lg-8">
          <h2>${title}</h2>
          <form:form method="post" modelAttribute="track">
            <form:hidden path="id" />

            <!-- Nom de la Piste -->
            <div class="form-floating mb-3">
              <form:input
                path="name"
                type="text"
                class="form-control"
                required="required"
              />
              <form:label path="name">Nom de la Piste</form:label>
              <form:errors path="name" cssClass="text-warning" />
            </div>

            <!-- Sélection de l'Album -->
            <div class="form-floating mb-3">
              <form:select path="album" class="form-control">
                <form:option value="">-- Sélectionner un album --</form:option>
                <c:forEach var="album" items="${albums}">
                  <form:option value="${album.id}">
                    ${album.title}
                  </form:option>
                </c:forEach>
              </form:select>
              <form:label path="album">Album</form:label>
              <form:errors path="album" cssClass="text-warning" />
            </div>

            <!-- Sélection du Genre -->
            <div class="form-floating mb-3">
              <form:select path="genre" class="form-control">
                <form:option value="">-- Sélectionner un genre --</form:option>
                <c:forEach var="genre" items="${genres}">
                  <form:option value="${genre.genreId}"> ${genre.name} </form:option>
                </c:forEach>
              </form:select>
              <form:label path="genre">Genre</form:label>
              <form:errors path="genre" cssClass="text-warning" />
            </div>

            <!-- Sélection du Type de Média -->
            <div class="form-floating mb-3">
              <form:select
                path="mediaType"
                class="form-control"
                required="required"
              >
                <form:option value=""
                  >-- Sélectionner un type de média --</form:option
                >
                <c:forEach var="mediaType" items="${mediaTypes}">
                  <form:option value="${mediaType.mediaTypeId}">
                    ${mediaType.name}
                  </form:option>
                </c:forEach>
              </form:select>
              <form:label path="mediaType">Type de Média</form:label>
              <form:errors path="mediaType" cssClass="text-warning" />
            </div>

            <!-- Compositeur -->
            <div class="form-floating mb-3">
              <form:input path="composer" type="text" class="form-control" />
              <form:label path="composer">Compositeur</form:label>
              <form:errors path="composer" cssClass="text-warning" />
            </div>

            <!-- Durée en Millisecondes -->
            <div class="form-floating mb-3">
              <form:input
                path="milliseconds"
                type="number"
                class="form-control"
                required="required"
              />
              <form:label path="milliseconds"
                >Durée (en millisecondes)</form:label
              >
              <form:errors path="milliseconds" cssClass="text-warning" />
            </div>

            <!-- Taille en Octets -->
            <div class="form-floating mb-3">
              <form:input path="bytes" type="number" class="form-control" />
              <form:label path="bytes">Taille (en octets)</form:label>
              <form:errors path="bytes" cssClass="text-warning" />
            </div>

            <!-- Prix Unitaire -->
            <div class="form-floating mb-3">
              <form:input
                path="unitPrice"
                type="number"
                step="0.01"
                class="form-control"
                required="required"
              />
              <form:label path="unitPrice">Prix Unitaire</form:label>
              <form:errors path="unitPrice" cssClass="text-warning" />
            </div>

            <button type="submit" class="btn btn-success">Enregistrer</button>
          </form:form>
        </div>
      </div>
    </section>
    <script
      src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
      integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi"
    ></script>
  </body>
</html>
