import { Form, useNavigate, useParams } from "react-router-dom";
import { authApi, endpoints } from "../configs/Apis";
import { useEffect, useState, useRef } from "react";
import { Button, Card, Col, Container, FloatingLabel, ListGroup, Row, Table } from "react-bootstrap";
import MySpinner from "../layout/MySpinner";
import { useReactToPrint } from "react-to-print";

const Payment = () => {
    const { appointmentId } = useParams();
    // const [appointment, setAppointment] = useState([]);
    const [prescription, setPrescription] = useState([]);
    const [prescriptionMedicine, setPrescriptionMedicine] = useState([]);
    const [rule, setRule] = useState([]);
    const [total, setTotal] = useState(0);
    const componentPDF = useRef();
    const [moneyReceived, setMoneyReceived] = useState(null);
    const [paymentMethod, setPaymentMethod] = useState('cash');

    const nav = useNavigate();

    // const loadAppointment = async () => {
    //     try {
    //         let e = endpoints['appointments'];
    //         e = `${e}${appointmentId}/`;
    //         let res = await authApi().get(e);
    //         setAppointment(res.data);
    //         console.log(res.data);
    //     } catch (error) {
    //         console.error("failed", error);
    //     }
    // }

    const loadPrescription = async () => {
        try {
            let e = endpoints['prescription'];
            e = `${e}?appointmentId=${appointmentId}`;
            let res = await authApi().get(e);
            setPrescription(res.data);
            console.log(res.data);

            if (res.data.id !== null) {
                console.log(res.data.id);
                loadPrescriptionMedicine(res.data.id);
            }
        } catch (error) {
            console.error("failed", error);
        }
    }

    const loadPrescriptionMedicine = async (id) => {
        try {
            let e = endpoints['rescriptions-medicines'];
            e = `${e}${id}/`;
            let res = await authApi().get(e);
            setPrescriptionMedicine(res.data);
            console.log(res.data);
            let total = 0;
            res.data.forEach((pm) => {
                total += pm.price * pm.quantity;
            });
            setTotal(total);
        } catch (error) {
            console.error("failed", error);
        }
    }

    const loadRule = async () => {
        try {
            let res = await authApi().get(endpoints['rules']);
            setRule(res.data);
        } catch (error) {
            console.log("failed", error);
        }
    }

    const save = async () => {

    }


    useEffect(() => {
        loadPrescription();
        loadRule();
    }, []);


    function formatDate(dateTimeString) {
        const options = { year: 'numeric', month: 'numeric', day: 'numeric' };
        return new Date(dateTimeString).toLocaleDateString(undefined, options);
    }


    const exportToPDf = useReactToPrint({
        content: () => componentPDF.current,
        documentTitle: "prescription"
    });

    const addPayment = (evt) => {
        evt.preventDefault();

        if(moneyReceived !== null) {
            if(moneyReceived >= total + rule.fee) {
                const process = async () => {
                    try {
                        let res = await authApi().post(endpoints['payments'], {
                            moneyReceived: moneyReceived,
                            paymentMethod: paymentMethod,
                            patientId: prescription.appointmentId.patientId.id,
                            appoimentId: appointmentId,
                            fee: rule.fee
                        });
        
                        console.log("successfully", res.data);
        
                        nav("/appointments")
                    } catch (error) {
                        console.error("falied", error);
                    }
                }
        
                process();
        
                exportToPDf();
            } else {
                alert("invalid money");
            }
        } else {
            alert("please enter received money");
        }
    }

    return (
        <>
            <Container ref={componentPDF}>
                <h1 className="text text-center text-info">Prescription</h1>
                <Card>
                    {prescription && prescription.appointmentId && prescription.doctorId ? (
                        <>
                            <Card.Body>
                                <Row>
                                    <Col>
                                        <h4>Patient's information</h4>
                                        <p>Full name: {prescription.appointmentId.patientId?.firstName} {prescription.appointmentId.patientId?.lastName}</p>
                                        <p>Date of birth: {formatDate(prescription.appointmentId.patientId?.dateOfBirth)}</p>
                                    </Col>
                                    <Col>
                                        <h4>Doctor's information</h4>
                                        <p>Full name: {prescription.doctorId.firstName} {prescription.doctorId.lastName}</p>
                                    </Col>
                                </Row>
                                <hr />
                                <h4>Symptom</h4>
                                <p>{prescription.symptom}</p>
                                <hr />
                                <h4>Diagnose</h4>
                                <p>{prescription.diagnose}</p>
                                <hr />
                                <h4>Medicine list</h4>
                                <hr />
                                <Table striped bordered hover>
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Quantity</th>
                                            <th>Unit</th>
                                            <th>Price</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {prescriptionMedicine && prescriptionMedicine.map((pm) => (
                                            <tr key={pm.id}>
                                                <td>{pm.medicineId.name}</td>
                                                <td>{pm.quantity}</td>
                                                <td>{pm.medicineId.unitId.name}</td>
                                                <td>${pm.price}</td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </Table>
                                <h4>Medicine</h4>
                                <p>${total}</p>
                                <h4>Fee</h4>
                                <p>${rule.fee}</p>
                                <h4>Total</h4>
                                <p>${total + rule.fee}</p>
                            </Card.Body>
                            <label htmlFor="moneyReceived">Money received:</label>
                            <input
                                type="number"
                                id="moneyReceived"
                                name="moneyReceived"
                                value={moneyReceived}
                                onChange={e => setMoneyReceived(e.target.value)}
                            />
                            <label htmlFor="paymentMethod">Payment method:</label>
                            <select
                                id="paymentMethod"
                                name="paymentMethod"
                                value={paymentMethod}
                                onChange={(e) => setPaymentMethod(e.target.value)}>
                                <option value="cash">Cash</option>
                                <option value="banking">Banking</option>
                            </select>

                            <Button onClick={addPayment} className="btn btn-danger mt-2 mb-2" >Save</Button>
                        </>
                    ) : (
                        <MySpinner />
                    )}
                </Card>
            </Container>
        </>
    );
}

export default Payment;