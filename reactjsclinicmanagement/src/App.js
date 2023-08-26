import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./layout/Header";
import Home from "./components/Home";
import Footer from "./layout/Footer";
import 'bootstrap/dist/css/bootstrap.min.css';
import Appointment from "./components/Appointment";
import ConfirmAppointment from "./components/ConfirmAppointment";
import Login from "./components/Login";
import { createContext, useReducer } from "react";
import MyUserReducer from "./reducers/MyUserReducer";
import cookie from "react-cookies";
import MedicalExamination from "./components/MedicalExamination";
import Payment from "./components/Payment";
import Shift from "./components/Shift";
import Register from "./components/Register";

export const MyUserContext = createContext();

const App = () => {
  const token = cookie.load("token");
  const u = cookie.load("user");
  let init;
  if (token !== null && u !== null) {
    init = cookie.load("user");
  }
  const [user, dispatch] = useReducer(MyUserReducer, init || null);

  return (
    <MyUserContext.Provider value={[user, dispatch]}>
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/create-appointment" element={<Appointment />} />
          <Route path="/appointments" element={<ConfirmAppointment />} />
          <Route path="/login" element={<Login />} />
          <Route path="/medical-examination/:appointmentId" element={<MedicalExamination />} />
          <Route path="/payment/:appointmentId" element={<Payment />} />
          <Route path="/shift" element={<Shift/>} />
          <Route path="/register" element={<Register/>} />
        </Routes>
        <Footer />
      </BrowserRouter>
    </MyUserContext.Provider>
  )
}

export default App;