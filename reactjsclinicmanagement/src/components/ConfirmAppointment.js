import React, { useState, useEffect, useContext } from 'react';
import { Button, Container, Table } from 'react-bootstrap';
import { authApi, endpoints } from '../configs/Apis';
import MySpinner from '../layout/MySpinner';
import { MyUserContext } from '../App';
import { Link, useNavigate } from 'react-router-dom';

const ConfirmAppointment = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const [appointments, setAppointments] = useState([]);

    const nav = useNavigate();

    const loadAppointments = async () => {
        try {
            let e = endpoints['appointments'];
            e = `${e}?role=${user.roleId.name}&id=${user.id}`;
            let res = await authApi().get(e);
            setAppointments(res.data);
            console.log(res.data);
        } catch (error) {
            console.error("failed", error);
        }
    }

    // const loadAppointments = async () => {
    //     try {
    //         let res = await Apis.get(endpoints['appointments'])
    //         setAppointments(res.data);
    //         console.log(res.data);
    //     } catch (error) {
    //         if (error.response) {
    //             // Xử lý lỗi từ phía máy chủ (có phản hồi từ máy chủ)
    //             console.error("Lỗi từ máy chủ:", error.response.data);
    //         } else if (error.request) {
    //             // Xử lý lỗi trong quá trình gửi yêu cầu
    //             console.error("Lỗi trong quá trình gửi yêu cầu:", error.request);
    //         } else {
    //             // Xử lý lỗi không xác định
    //             console.error("Lỗi không xác định:", error.message);
    //         }
    //     }
    // }


    useEffect(() => {
        loadAppointments();
    }, [])

    const handleConfirm = async (a) => {
        try {
            const confirmAppointment = await authApi().post(endpoints['appointments'], {
                "id": a.id,
                "appointmentTime": a.appointmentTime,
                "patientId": a.patientId
            });

            if (confirmAppointment.status === 200) {
                console.log('successfully');
                loadAppointments();
            }
        } catch (error) {
            console.error("failed", error);
        }
    }

    const handleDelete = async (id) => {
        try {
            let e = endpoints['appointments'];
            e = `${e}${id}/`;
            await authApi().delete(e);
            alert("deleted");
            loadAppointments();
        } catch (error) {
            alert("falied");
        }
    }

    const handleGo = (a) => {
        return nav(`/medical-examination/?appointmentId=${a.id}`);
    }

    function formatDate(dateTimeString) {
        const options = { year: 'numeric', month: 'numeric', day: 'numeric', hour: 'numeric', minute: 'numeric' };
        return new Date(dateTimeString).toLocaleDateString(undefined, options);
    }

    if (appointments === null) {
        return <MySpinner />;
    }

    return (
        <Container>
            <h1 className='text text-center text-info'>My ConfirmAppointment Page</h1>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Status</th>
                        <th>Patient</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {appointments && appointments.map((appointment) => (
                        <tr key={appointment.id}>
                            <td>{formatDate(appointment.createdDate)}</td>
                            <td>{appointment.status}</td>
                            <td>{appointment.patientId.firstName} {appointment.patientId.lastName}</td>
                            <td>
                                {user.roleId.name === "ROLE_NURSE" ? (
                                    appointment.status === "Pending confirmation" ? (
                                        <Button variant="success" onClick={() => handleConfirm(appointment)}>Confirm</Button>
                                    ) : (
                                        <Link to={`/payment/${appointment.id}`} className="btn btn-warning" >Go</Link>
                                    )
                                ) : user.roleId.name === "ROLE_DOCTOR" ? (
                                    <Link to={`/medical-examination/${appointment.id}`} className="btn btn-warning" >Go</Link>
                                ) : (
                                    appointment.status === "Pending confirmation" && <Button variant="danger" onClick={() => handleDelete(appointment.id)}>Delete</Button>
                                )}
                            </td>
                        </tr>
                    ))}
                </tbody>

            </Table>
        </Container>
    );
};

export default ConfirmAppointment;
