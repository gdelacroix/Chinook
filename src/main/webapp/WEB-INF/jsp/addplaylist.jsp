<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib
uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <title>Ajouter Playlist</title>
    <link
      href="/webjars/bootstrap/5.2.0/css/bootstrap.min.css"
      rel="stylesheet"
    />
  </head>

  <body>
    <div class="container mt-5">
      <h2>Ajouter une Playlist</h2>
      <form:form
        method="post"
        modelAttribute="playlist"
        id="playlistForm"
        action="/playlists/add"
      >
        <form:hidden path="playlistId" />

        <div class="mb-3">
          <form:label path="name" class="form-label"
            >Nom de la Playlist</form:label
          >
          <form:input
            path="name"
            id="playlistName"
            type="text"
            class="form-control"
            required="required"
          />
          <form:errors path="name" cssClass="text-danger" />
        </div>

        <div class="mb-3">
          <label for="trackSelect" class="form-label">Ajouter un titre :</label>
          <select id="trackSelect" class="form-select">
            <option value="">Sélectionner un titre</option>
            <c:forEach var="track" items="${tracks}">
              <option name="trackIds" value="${track.id}">${track.name}</option>
            </c:forEach>
          </select>
        </div>

        <table class="table mt-3">
          <thead>
            <tr>
              <th>Nom du titre</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody id="playlistTracks"></tbody>
        </table>

        <input type="hidden" id="selectedTracks" name="trackIds" />
        <button type="submit" class="btn btn-success">Valider</button>
      </form:form>
    </div>

    <script>
      const playlistForm = document.getElementById("playlistForm");
      const selectedTracksField = document.getElementById("selectedTracks");
      document.addEventListener("DOMContentLoaded", function () {
        const trackSelect = document.getElementById("trackSelect");
        const playlistTracks = document.getElementById("playlistTracks");

        let selectedTracks = [];

        trackSelect.addEventListener("change", function () {
          // const trackId = trackSelect.value;
          // const trackName = trackSelect.options[trackSelect.selectedIndex].text;
          // if (trackId && !selectedTracks.some((t) => t.id === trackId)) {
          //   selectedTracks.push({ id: trackId, name: trackName });
          //   updatePlaylistTable();
          // }
          const selectedOption = trackSelect.options[trackSelect.selectedIndex];

          if (
            selectedOption.value &&
            !selectedTracks.includes(selectedOption.value)
          ) {
            selectedTracks.push(selectedOption.value);
         
            var optionchoisie = selectedOption.text;
           
            // Ajouter à la table HTML
            const row = document.createElement("tr");
            const nameCell = document.createElement("td");
            nameCell.textContent = optionchoisie; // Ajout direct du texte
            const actionCell = document.createElement("td");

            const removeButton = document.createElement("button");
            removeButton.textContent = "Supprimer";
            removeButton.classList.add(
              "btn",
              "btn-danger",
              "btn-sm",
              "remove-track"
            );
            removeButton.setAttribute("data-id", selectedOption.value);

            actionCell.appendChild(removeButton);
            row.appendChild(nameCell);
            row.appendChild(actionCell);

            playlistTracks.appendChild(row);

            // Mettre à jour le champ hidden avec uniquement les IDs
            selectedTracksField.value = selectedTracks.join(",");
          }
        });

        // Suppression d'un track de la playlist
        playlistTracks.addEventListener("click", function (e) {
          if (e.target.classList.contains("remove-track")) {
            const trackId = e.target.getAttribute("data-id");
            selectedTracks = selectedTracks.filter((id) => id !== trackId);
            selectedTracksField.value = selectedTracks.join(",");

            // Supprimer la ligne du tableau
            e.target.closest("tr").remove();
          }
        });

        // function updatePlaylistTable() {
        //   playlistTracks.innerHTML = "";
        //   selectedTracks.forEach((track) => {
        //     console.log("Ajout du track:", track.id, track.name);

        //     if (!track.id || !track.name) {
        //       console.warn("Track invalide détecté:", track);
        //       return;
        //     }

        //     const row = document.createElement("tr");
        //     // Création sécurisée des valeurs
        //     const trackName = document.createTextNode(track.name);
        //     const trackId = track.id;

        //     // Création des cellules
        //     const nameCell = document.createElement("td");
        //     nameCell.appendChild(trackName);

        //     const buttonCell = document.createElement("td");
        //     const deleteButton = document.createElement("button");
        //     deleteButton.textContent = "Supprimer";
        //     deleteButton.className = "btn btn-danger btn-sm";
        //     deleteButton.setAttribute("type", "button");
        //     deleteButton.onclick = () => removeTrack(trackId);

        //     buttonCell.appendChild(deleteButton);

        //     // Ajouter les cellules à la ligne
        //     row.appendChild(nameCell);
        //     row.appendChild(buttonCell);

        //     console.log("Row HTML:", row.outerHTML);
        //     playlistTracks.appendChild(row);
        //   });
        //   updateSelectedTracksField();
        // }

        // window.removeTrack = function (trackId) {
        //   selectedTracks = selectedTracks.filter(
        //     (track) => track.id != trackId
        //   );
        //   updatePlaylistTable();
        // };

        // function updateSelectedTracksField() {
        //   selectedTracksField.value = JSON.stringify(
        //     selectedTracks.map((t) => t.id)
        //   );
        // }
      });

      // Gérer l'envoi du formulaire
      // playlistForm.addEventListener("submit", function (event) {
      //   event.preventDefault(); // Empêcher le rechargement de la page

      //   // Vérifier que selectedTracksField.value contient bien un JSON valide
      //   try {
      //     selectedTracks = JSON.parse(selectedTracksField.value); // Convertir en tableau
      //   } catch (error) {
      //     console.error("Erreur de parsing JSON :", error);
      //     alert("Erreur lors de la récupération des pistes sélectionnées !");
      //     return;
      //   }

      //   if (!Array.isArray(selectedTracks)) {
      //     console.error(
      //       "selectedTracks n'est pas un tableau :",
      //       selectedTracks
      //     );
      //     alert(
      //       "Erreur interne : Les pistes sélectionnées ne sont pas valides."
      //     );
      //     return;
      //   }

      //   const formData = new FormData();
      //   formData.append("name", document.getElementById("playlistName").value);

      //   // Ajouter les trackIds sous forme de paramètres multiples
      //   selectedTracks.forEach((trackId) =>
      //     formData.append("trackIds", trackId)
      //   );

      //   fetch("/playlists/add", {
      //     method: "POST",
      //     headers: {
      //       "Content-Type": "application/json",
      //     },
      //     body: JSON.stringify({
      //       name: document.getElementById("playlistName").value,
      //       trackIds: selectedTracks.map((id) => parseInt(id)), // Convertir en nombres
      //     }),
      //   })
      //     .then((response) => {
      //       if (response.ok) {
      //         window.location.href = "/playlists"; // Rediriger après succès
      //       } else {
      //         alert("Erreur lors de l'enregistrement !");
      //       }
      //     })
      //     .catch((error) => console.error("Erreur : ", error));
      // });
    </script>
  </body>
</html>
