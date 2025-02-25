<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
  <head>
    <meta charset="utf-8" />

    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />

    <meta name="description" content="" />

    <meta name="author" content="" />
    <title>Liste des Clients</title>
    <title>Welcome</title>

    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />

    <!-- Core theme CSS (includes Bootstrap)-->

    <link
      href="/webjars/bootstrap/5.2.0/css/bootstrap.min.css"
      rel="stylesheet"
    />

    <link href="/css/styles.css" rel="stylesheet" />
  </head>
  <body id="page-top">
    <!-- Navigation-->

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
          <span class="navbar-toggler-icon"> </span>
        </button>

        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ms-auto">
            <li class="nav-item">
              <a class="nav-link" href="#employees">Employés</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#customers">Clients</a>
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
          <div class="col-lg-12">
            <h2>Nos Clients</h2>
            <p class="lead">Voici la liste de nos clients</p>

            <div class="text-end">
              <a
                type="button"
                class="btn btn-primary btn-md"
                href="/customers/add"
                >Ajouter Client</a
              >
            </div>
            <table class="table">
              <thead class="thead-dark">
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Entreprise</th>
                  <th scope="col">Nom</th>
                  <th scope="col">Prénom</th>
                  <th scope="col">Email</th>
                  <th scope="col"></th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="customer" items="${customers}">
                  <tr>
                    <th scope="row">${customer.customerId}</th>
                    <td>${customer.company}</td>
                    <td>${customer.lastName}</td>
                    <td>${customer.firstName}</td>
                    <td>${customer.email}</td>
                    <td>
                        <a
                          type="button"
                          class="btn btn-success"
                          href="/customers/update?id=${customer.customerId}"
                          >Modifier</a
                        >
  
                        <a
                          type="button"
                          class="btn btn-warning"
                          href="/customers/delete?id=${customer.customerId}"
                          >Supprimer</a
                        >
                      </td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </section>
  </body>
</html>
