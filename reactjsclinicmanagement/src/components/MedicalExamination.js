import { useContext, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { MyUserContext } from "../App";
import Apis, { authApi, endpoints } from "../configs/Apis";
import { Button, FloatingLabel, Form, ListGroup, Table } from "react-bootstrap";

const MedicalExamination = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const { appointmentId } = useParams();
    const [appointment, setAppointment] = useState([]);
    const [medicines, setMedicines] = useState([]);
    const [keyword, setKeyword] = useState('');
    const [medicineList, setMedicineList] = useState([]);
    const [quantity, setQuantity] = useState(1);
    const [selectedMedicines, setSelectedMedicines] = useState([]);
    const [symptom, setSymptom] = useState(null);
    const [diagnose, setDiagnose] = useState(null);
    const [prescriptions, setPrescriptions] = useState([]);
    const nav = useNavigate();
    const [isTableVisible, setIsTableVisible] = useState(false);

    const loadAppointment = async () => {
        try {
            let e = endpoints['appointments'];
            e = `${e}${appointmentId}/`;
            let res = await authApi().get(e);
            setAppointment(res.data);
            console.log(res.data);
        } catch (error) {
            console.error("failed", error);
        }
    }

    const loadMedicines = async () => {
        try {
            let res = await authApi().get(endpoints['medicines']);
            setMedicines(res.data);
            console.log(res.data);
        } catch (error) {
            console.error("failed", error);
        }
    }

    const loadHistory = async (id) => {
        try {
            let e = endpoints['prescription'];
            e = `${e}?patientId=${id}`;
            let res = await authApi().get(e);
            setPrescriptions(res.data);
            console.log(res.data);
        } catch (error) {
            console.error("failed", error);
        }
    }

    useEffect(() => {
        loadAppointment();
        loadMedicines();
    }, [])

    const searchMedicines = (kw) => {
        return medicines.filter((medicine) =>
            medicine.name.toLowerCase().includes(keyword.toLowerCase())
        );
    };

    const handleSearch = (e) => {
        const kw = e.target.value;
        setKeyword(kw);

        const filteredMedicines = searchMedicines(kw);
        setMedicineList(filteredMedicines);
    }

    const handleSelectedMedicine = (id, quantity) => {

        const check = selectedMedicines.findIndex((m) => m.id === id);

        if (check !== -1) {
            const q = parseInt(selectedMedicines[check].quantity);
            const m = medicines.find((medicine) => medicine.id === id);

            if (q + parseInt(quantity) <= parseInt(m.quantity)) {
                const updatedSelectedMedicines = [...selectedMedicines];
                updatedSelectedMedicines[check].quantity = q + parseInt(quantity);
                setSelectedMedicines(updatedSelectedMedicines);
                setQuantity(1);
            } else {
                alert(`must be less than or equal ${m.quantity}`);
            }
        } else {
            const m = medicines.find((medicine) => medicine.id === id);

            if (parseInt(quantity) <= parseInt(m.quantity)) {
                setSelectedMedicines((prev) => [...prev, { ...m, quantity: parseInt(quantity) }]);
                setQuantity(1);
            } else {
                alert(`must be less than or equal ${m.quantity}`);
            }
        }
    };



    const handleRemoveMedicine = (id) => {
        const updatedSelectedMedicines = selectedMedicines.filter((medicine) => medicine.id !== id);

        setSelectedMedicines(updatedSelectedMedicines);
    };

    const addPrescriptionMedicine = (evt) => {
        evt.preventDefault();

        if (symptom !== null && diagnose !== null && selectedMedicines.length !== 0) {
            const process = async () => {
                try {
                    let res = await authApi().post(endpoints['rescriptions-medicines'], {
                        symptom: symptom,
                        diagnose: diagnose,
                        appointmentId: appointmentId,
                        medicines: selectedMedicines,
                    });

                    console.log("successfully", res.data);

                    nav("/appointments")
                } catch (error) {
                    console.error("falied", error);
                }
            }

            process();
        } else {
            alert("please enter all of information");
        }
    }

    function formatDate(dateTimeString) {
        const options = { year: 'numeric', month: 'numeric', day: 'numeric' };
        return new Date(dateTimeString).toLocaleDateString(undefined, options);
    }

    const toggleTable = () => {
        setIsTableVisible(!isTableVisible);
        loadHistory(appointment.patientId.id);
    };

    return (
        <>
            <h1 className="text text-center text-success">Medical Examination and Prescription</h1>

            <div className="container">
                    <Button variant="info" className="mb-2" onClick={toggleTable}>Check history</Button>
                    {isTableVisible && (
                        <Table striped bordered hover>
                            <thead>
                                <tr>
                                    <th>Date</th>
                                    <th>Symptom</th>
                                    <th>Diagnose</th>
                                </tr>
                            </thead>
                            <tbody>
                                {prescriptions && prescriptions.map((prescription) => (
                                    <tr key={prescription.id}>
                                        <td>{formatDate(prescription.createdDate)}</td>
                                        <td>{prescription.symptom}</td>
                                        <td>{prescription.diagnose}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </Table>
                    )}
                <Form onSubmit={addPrescriptionMedicine}>
                    <FloatingLabel controlId="symptom" label="Enter symptom here" className="mb-3">
                        <Form.Control type="text" placeholder="..." value={symptom} onChange={e => setSymptom(e.target.value)} />
                    </FloatingLabel>

                    <FloatingLabel controlId="diagnose" label="Enter diagnose here" className="mb-3">
                        <Form.Control type="text" placeholder="..." value={diagnose} onChange={e => setDiagnose(e.target.value)} />
                    </FloatingLabel>

                    <FloatingLabel controlId="keyword" label="Enter medicine's name to find medicine here" className="mb-3">
                        <Form.Control type="text" placeholder="..." value={keyword} onChange={handleSearch} />
                    </FloatingLabel>
                    <Table striped bordered hover>
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Instock</th>
                                <th>Unit</th>
                                <th>Price</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {medicineList && medicineList.map((medicine) => (
                                <tr key={medicine.id}>
                                    <td>{medicine.name}</td>
                                    <td>
                                        <FloatingLabel controlId="quantity" label="Quantity" className="mb-3">
                                            <Form.Control type="number" placeholder="..." value={quantity} onChange={e => setQuantity(e.target.value)} />
                                        </FloatingLabel>
                                    </td>
                                    <td>{medicine.quantity}</td>
                                    <td>{medicine.unitId.name}</td>
                                    <td>${medicine.price}</td>
                                    <td><Button variant="dark" onClick={() => handleSelectedMedicine(medicine.id, quantity)} >Add to list</Button></td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>

                    <Table striped bordered hover>
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Unit</th>
                                <th>Price</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {selectedMedicines && selectedMedicines.map((medicine, index) => (
                                <tr key={index}>
                                    <td>{medicine.name}</td>
                                    <td>{medicine.quantity}</td>
                                    <td>{medicine.unitId.name}</td>
                                    <td>${medicine.price}</td>
                                    <td><Button variant="dark" onClick={() => handleRemoveMedicine(medicine.id)} >Remove</Button></td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                    <Button type="submit" variant="success" className="mb-2" size="lg">Add prescription</Button>
                </Form>


            </div>
        </>
    )
}

export default MedicalExamination;