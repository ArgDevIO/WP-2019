# Лабораториска вежба 1

## Потребно предзнаење
За да разберете како функционираат концептите опфатени во оваа лабораториска вежба, потребно е да прочитате: 
**- Head First Servlets and JSP, Second Edition**
    - **Chapter 4**: Being a Servlet: request AND response 
    - **Chapter 5**: Being a Web App: attributes and listeners 
    - **Chapter 6**: Conversational state: session management 
**- Spring in Action (5th edition)** 
    - **Chapter 1**: Getting started with Spring

## Спецификација за лабораториската вежба
1. Креирајте нов Spring Boot проект со група mk.finki.ukim.mk и artefactId=lab кој ги има истите зависности како проектот од аудиториските вежби (зависностите може да ги видите во <dependency> тагвите во pom.xml.
2. Дефинирајте пакет mk.ukim.finki.wp.lab.model и во него креирајте ја Order класата. Таа треба да содржи:
  * String pizzaType
  * String pizzaSize
  * String clientName,
  * String clientAddressи
  * Long orderId.
3. Во mk.ukim.finki.wp.lab.model креирајте Pizza класа која ќе содржи:
  * String name,
  * String description
4. Креирајте класа PizzaRepository во пакетот mk.ukim.finki.wp.lab.repository, во која ќе чувате List<Pizza> иницијализирана со 10 вредности.
  * Имплементирајте метод public List<Pizza> getAllPizzas(); кој само ќе ја врати листата.
5. Дефинирајте ги следните интерфејси во mk.ukim.finki.wp.lab.service кои ќе ги претставуваат бизнис функционалностите на апликацијата:
  ```Java
  public interface PizzaService {
       List<Pizza> listPizzas();
  }
  public interface OrderService{
      Order placeOrder(String pizzaType, String clientName, String address);
  }
  ```
6. Имплементирајте ги сервисите (PizzaService треба да зависи од PizzaRepository).
7. Креирајте сервлет ShowPizza во пакетот mk.ukim.finki.lab.web и мапирајте го на патеката /. Овој сервлет треба да зависи од PizzaService и да ги прикаже сите добиени пици од методот listPizzas().

  * Прилагодете го фајлот listPizzas.html за изгледот на оваа страница.
  ```HTML
  <html>
  <head>
      <meta charset="utf-8">
      <title>Pizza Order page - Welcome and choose pizza</title>
      <style type="text/css">
          body {
              width: 800px;
              margin: auto;
          }
      </style>
  </head>
  <body>
  <header>
      <h1>Welcome to our Pizza Delivery App</h1>
  </header>
  <main>
      <h2>Choose pizza:</h2>
      <input type="radio" name="pizza" value="Margherita"> Margherita (tomato sauce, mozzarella)<br/>
      <input type="radio" name="pizza" value="Carbonara"> Carbonara (fresh cream, mozzarella, bacon)<br/>
      <input type="radio" name="pizza" value="Vegetariana"> Vegetariana (tomato sauce, mushrooms)<br/>
      <input type="radio" name="pizza" value="Calzone"> Calzone (Pizza dough, ricotta, pepperoni, pizza sauce, olive oil) <br/>
      <input type="radio" name="pizza" value="Cheddar"> Cheddar (cheddar, tomato sauce) <br/>
      <input type="radio" name="pizza" value="Capricciosa"> Capricciosa (tomato sauce, cheese, ham) <br/>
      <input type="radio" name="pizza" value="Burger Classic"> Burger Classic (barbecue sauce, beef, mozzarella, onions)<br/>
      <input type="radio" name="pizza" value="Boston Barbecue"> Burger Barbecue (ham, chicken meat, onions)<br/>
      <input type="radio" name="pizza" value="Vegie Vulcano"> Pepperoni (tomato sauce, mozzarella, sausage) <br/>
      <input type="radio" name="pizza" value="Boston Barbecue"> Quattro Formaggi (Taleggio, Mascarpone, Gorgonzola, Parmesan) <br/>
      <br/>
      <a href="/selectPizzaSize.html">Submit</a>
  </main>
  </body>
  </html>
8. При избор на пица, треба да го запаметите изборот. За оваа цел креирајте сервлет SelectPizza мапиран на /selectPizza.do.

  * Овој сервлет треба да ја прикажете страната за избор на големина на пица
  * Во фолдерот src/main/resources/templates додадете фајл selectPizzaSize.html.
  * Во страницата треба да има форма од која ќе се избере една големината на пицата.
  * Типот на елементите во формата е radio.
  * При клик на Submit копчето од оваа форма треба да се повика сервлетот на адресата /PizzaOrder.do.
  * Прилагодете го фајлот selectPizzaSize.html за изгледот на оваа страница.
  ```HTML
  <html>
  <head>
      <meta charset="utf-8">
      <title>Pizza Order page - Pizza Size</title>
      <style type = "text/css">
          body {
              width: 800px;
              margin: auto;
          }
          table {
              width:100%;
          }
          table, td, th {
              border: 1px solid black;
              padding:3px 2px;
          }
          section {
              float: left;
              margin: 0 1.5%;
              width: 63%;
          }
          aside {
              float: right;
              margin: 0 1.5%;
              width: 30%;
          }
      </style>
  </head>
  <body>
      <header>
          <h1>Pizza Order page - Select Pizza Size </h1>
      </header>
      <section>
          <h2>Choose pizza size:</h2>
          <input type="radio" name="pizza_size" value="Small"> Small <br/>
          <input type="radio" name="pizza_size" value="Medium"> Medium <br/>
          <input type="radio" name="pizza_size" value="Big"> Big <br/>
          <input type="radio" name="pizza_size" value="Family"> Family <br/>
          <br/>
          <a href="/deliveryInfo.html">Submit</a>
      </section>
      <aside>
          <table>
              <tr>
                  <th colspan="2">
                      Your Order Status
                  </th>
              </tr>
              <tr>
                  <td><b>Pizza Type </b></td>
                  <td>Margarita</td>
              </tr>
          </table>
      </aside>

  </body>
  </html>
  ```
9. Креирајте сервлет со име PizzaOrder во пакетот mk.ukim.finki.wp.lab.web и мапирајте го на /PizzaOrder.do патеката.
10. Овој сервлет треба да ја постави вредноста за големината на пицата во сесија и да го прикаже темплејтот deliveryInfo.html.

  * За да поставите информации во сесија, искористете: request.getSession().setAttribute(“yourAttributeName”, attributeValue)
  * Прилагодете го фајлот deliveryInfo.html за изгледот на оваа страница.
  ```HTML
  <html>
         <head>
             <meta charset="utf-8">
             <title>WP lab - Delivery Info</title>
             <style type="text/css">
                 body {
                     width: 800px;
                     margin: auto;
                 }
                 table {
                     width: 100%;
                 }

                 table, td, th {
                     border: 1px solid black;
                     padding: 3px 2px;
                 }

                 section {
                     float: left;
                     margin: 0 1.5%;
                     width: 63%;
                 }

                 aside {
                     float: right;
                     margin: 0 1.5%;
                     width: 30%;
                 }
             </style>
         </head>
         <body>
             <header>
                 <h1>Pizza Order page - Delivery information </h1>
             </header>
             <section>

                 <form method="" action="">
                     <label for="clientName">Client Name:</label><br/>
                     <input type="text" id="clientName" name="clientName"/><br/>
                     <label for="clientAddress">Delivery Address:</label><br/>
                     <textarea cols="40" rows="3" id="clientAddress" name="clientAddress"></textarea><br/>
                     <a href="/confirmationInfo.html">Submit</a>
                 </form>

             </section>
             <aside>
                 <table>
                     <tr>
                         <th colspan="2">
                             Your Order Status
                         </th>
                     </tr>

                     <tr>
                         <td><b>Pizza Type </b></td>
                         <td>Margarita</td>
                     </tr>
                     <tr>
                         <td><b>Pizza Size </b></td>
                         <td>Large</td>
                     </tr>
                 </table>
             </aside>
         </body>
     </html>
     ```
11. Страната генерирана од овој сервлет има Submit копче кое не’ носи на сервлетот со локација /ConfirmationInfo.do.

12. Да се креира сервлет со име ConfirmationInfo, мапиран на патеката /ConfirmationInfo.do.

  * Овој сервлет треба да го испечати пребарувачот и оперативниот систем на корисникот, неговото име, адреса и големината на пица која ја избрал корисникот.
  * Копчето Log out треба да ја поништи тековната сесија на корисникот и да го редиректира на првата страница /.
  * Прилагодете го фајлот confirmationInfo.html за изгледот на оваа страница.
  ```HTML
  <html>
  <head>
      <meta charset="utf-8">
      <title>WP lab - Confirmation Info</title>
      <style type="text/css">
          body {
              width: 800px;
              margin: auto;
          }
          table {
              width:100%;
          }
          table, td, th {
              border: 1px solid black;
              padding: 3px 2px;
          }
      </style>
  </head>
  <body>
  <section>
      <header>
          <h1>Pizza Order page - Order confirmation </h1>
      </header>
      <table>
          <tr>
              <th colspan="2">
                  Your Order Status
              </th>
          </tr>
          <tr>
              <td><b>Client Name </b></td>
              <td>Petko Petkov</td>
          </tr>
          <tr>
              <td><b>Client Address </b></td>
              <td>Partizanska 5, Skopje</td>
          </tr>
          <tr>
              <td><b>Client IP Address</b></td>
              <td>127.0.0.1</td>
          </tr>
          <tr>
              <td><b>Client Browser</b></td>
              <td>Mozilla</td>
          </tr>
          <tr>
              <td><b>Pizza Type </b></td>
              <td>Margarita</td>
          </tr>
          <tr>
              <td><b>Pizza Size </b></td>
              <td>Large</td>
          </tr>
      </table>
      <div>
          <a href="/">Log out</a>
      </div>


  </section>
  </body>
  </html>
  ```
13. Да се воведе печатење на сите барања, креирања на сесии и креирање на апликацијата, за да можеме да имаме подобра контрола на она што се извршува во нашите апликации.

  * За оваа цел треба да креирате Listener
  * Во секој од имплементираните методи поставете System.out.println("[WP-Log] {methodName}"), каде ќе печатите во конзола кој е повиканиот метод и ќе може да увидите кој метод кога се повикува.
