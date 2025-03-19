<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Playlists</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <!-- <script
      src="https://kit.fontawesome.com/a076d05399.js"
      crossorigin="anonymous"
    ></script> -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <style>
      body {
        background-color: #121212;
        color: #fff;
        font-family: Arial, sans-serif;
      }
      .table-container {
        max-width: 90%;
        margin: auto;
        background-color: #181818;
        padding: 20px;
        border-radius: 15px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
      }
      table {
        table-layout: fixed;
        width: 100%;
        border-collapse: collapse;
        border-radius: 10px;
        overflow: hidden;
        white-space: nowrap; /* Empêche le retour à la ligne */
        overflow: hidden;
        text-overflow: ellipsis; /* Coupe le texte avec "..." si trop long */
      }
      thead {
        background-color: #1db954;
        color: white;
      }
      th,
      td {
        padding: 12px;
        text-align: left;
      }

      th:nth-child(1), td:nth-child(1) { width: 5%; }  /* Numéro */
      th:nth-child(2), td:nth-child(2) { width: 10%; } /* Catégorie */
      th:nth-child(3), td:nth-child(3) { width: 40%;  } /* Titre */
      th:nth-child(4), td:nth-child(4) { width: 20%;   } /* Artiste */
      th:nth-child(5), td:nth-child(5) { width: 10%; text-align: right; } /* Durée */
      th:nth-child(6), td:nth-child(6) { width: 15%; text-align: center; } /* Actions */

      tbody tr {
        transition: background 0.3s;
      }
      tbody tr:hover {
        background-color: rgba(255, 255, 255, 0.1);
      }
      .play-icon {
        cursor: pointer;
        color: #1db954;
      }
      .play-icon:hover {
        color: #fff;
      }
    </style>
  </head>
  <body id="page-top">
    <!-- Navigation -->
    <nav
      class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
      id="mainNav"
    >
      <div class="container-fluid">
        <a class="navbar-brand" href="#page-top">Gestion Musique</a>
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
              <a class="nav-link" href="/playlists">Playlists</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Main content -->
    <div class="container mt-5 pt-5">
      <h2>🎵 Liste des Playlists</h2>
      <div class="text-end mb-3">
        <a class="btn btn-primary" href="/playlists/add"
          >Ajouter une nouvelle playlist</a
        >
      </div>

      <!-- Table des Playlists -->
      <table class="table table-dark table-hover table-striped">
        <thead class="table-dark">
          <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Titres</th>
            <th>Artiste</th>
            <th>Durée</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <!-- Boucle pour afficher les playlists -->
          <c:forEach var="playlist" items="${playlists}">
            <tr>
                <td>${playlist.playlistId}</td>
                <td>${playlist.name}</td>
                <td>
                    <c:forEach var="track" items="${playlist.tracks}" varStatus="status">
                        <i class="fas fa-play-circle play-icon"></i> 
                        <c:choose>
                          <c:when test="${fn:length(track.name) > 30}">
                              ${fn:substring(track.name, 0, 30)}...
                          </c:when>
                          <c:otherwise>
                              ${track.name}
                          </c:otherwise>
                      </c:choose> <br>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="track" items="${playlist.tracks}" varStatus="status">
                      <c:choose>
                          <c:when test="${fn:length(track.name) > 20}">
                              ${fn:substring(track.album.artist.name, 0, 20)}...
                          </c:when>
                          <c:otherwise>
                              ${track.album.artist.name}
                          </c:otherwise>
                      </c:choose>
                     
                        <br>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="track" items="${playlist.tracks}" varStatus="status">
                        <c:set var="minutes" value="${Math.floor(track.milliseconds / 60000)}" />
                        <c:set var="seconds" value="${Math.floor((track.milliseconds % 60000) / 1000)}" />
                        ${fn:replace(minutes, '.0', '')} m ${fn:replace(seconds, '.0', '')} s <br>
                    </c:forEach>
                </td>
                <td>
                    <a href="/playlists/update?id=${playlist.playlistId}" class="btn btn-warning btn-sm">Modifier</a>
                    <a href="/playlists/delete?id=${playlist.playlistId}" class="btn btn-danger btn-sm" onclick="return confirm('Voulez-vous vraiment supprimer cette playlist ?');">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
        
        </tbody>
      </table>

      <!-- Pagination -->
      <!-- Pagination -->
      <div class="d-flex justify-content-between align-items-center mt-4">
        <!-- Bouton Précédent -->
        <c:if test="${currentPage > 0}">
            <a href="?page=${currentPage - 1}&size=50" class="btn btn-outline-light">⬅️ Précédent</a>
        </c:if>
        
        <span>Page ${currentPage + 1} sur ${totalPages}</span>
        
        <!-- Bouton Suivant -->
        <c:if test="${currentPage + 1 < totalPages}">
            <a href="?page=${currentPage + 1}" class="btn btn-outline-light">Suivant ➡️</a>
        </c:if>
      </div>

      
    </div>
    <script>
      document.querySelectorAll(".play-icon").forEach((icon) => {
        icon.addEventListener("click", () => {
          alert("Lecture en cours... (Simulation)");
        });
      });


    
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
