import React, { useContext, useState } from 'react';
import { Form, Container, Row, Col, Button } from 'react-bootstrap';
import Apis, { authApi, endpoints } from '../configs/Apis';
import { MyUserContext } from '../App';
import { Link } from 'react-router-dom';
import { format } from 'date-fns';

const Appointment = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const [selectedDate, setSelectedDate] = useState(new Date());
    const [selectedTime, setSelectedTime] = useState('09:00:00');
    const now = new Date();

    const handleDateChange = (event) => {
        const newDate = event.target.value;
        setSelectedDate(newDate);
    };

    const handleTimeChange = (event) => {
        setSelectedTime(event.target.value);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (selectedDate && selectedTime) {
            // Kết hợp ngày và thời gian thành datetime
            const dateTime = new Date(selectedDate + 'T' + selectedTime);

            console.log(dateTime.toISOString().slice(0, 24));

            // const formattedDateTime = format(dateTime, "yyyy-MM-dd'T'HH:mm:ss.SSSxxx");

            // console.log(formattedDateTime);

            if (dateTime < now) {
                alert("Invalid date and time");
            } else {
                try {
                    // Chuyển đổi đối tượng Date thành chuỗi theo định dạng ISO 8601
                    const formattedDateTime = dateTime.toISOString();

                    const response = await authApi().post(endpoints['appointments'], {
                        appointmentTime: dateTime.toISOString().slice(0, 24),
                        patientId: user
                    });

                    if (response.status === 200) {
                        console.log('Successfully');
                        setSelectedDate('');
                        setSelectedTime('');
                        alert("Appointment successfully booked!");
                    } else {
                        console.log(response.status);
                        alert("Fully booked today. Please choose another date and time.");
                    }
                } catch (error) {
                    console.error('Failed', error);
                }
            }
        } else {
            alert("Please select a date and time.");
        }
    };

    // const handleSubmit = async (e) => {
    //     e.preventDefault();

    //     const createdDate = selectedDate.toISOString();

    //     console.log(createdDate);

    //     if (selectedDate < now) {
    //         alert("invalid date");
    //     } else {
    //         try {
    //             const response = await authApi().post(endpoints['appointments'], {
    //                 appointmentTime: createdDate,
    //                 patientId: user
    //             });
    //             if (response.status === 200) {
    //                 console.log('successfully');
    //                 setSelectedDate(new Date());
    //                 alert("successfully");
    //             } else {
    //                 console.log(response.status);
    //                 alert("fully booked today");
    //             }
    //         } catch (error) {
    //             console.error('failed', error);
    //         }
    //     }
    // };

    return (
        <Container>
            <h1 className='text text-center text-success'>Book appoiment</h1>
            <Row className="justify-content-center">
                <Col md={6}>
                    <Form onSubmit={handleSubmit}>
                        <Form.Group controlId="appointmentDate">
                            <Form.Label>Choose a Date:</Form.Label>
                            <Form.Control
                                type="date"
                                value={selectedDate}
                                onChange={handleDateChange}
                                required
                            />
                        </Form.Group>
                        <Form.Group controlId="appointmentTime">
                            <Form.Label>Choose a Time:</Form.Label>
                            <Form.Control
                                as="select"
                                value={selectedTime}
                                onChange={handleTimeChange}
                                required
                            >
                                <option value="09:00:00">9:00 AM</option>
                                <option value="10:00:00">10:00 AM</option>
                                <option value="11:00:00">11:00 AM</option>
                                <option value="13:00:00">1:00 PM</option>
                                <option value="14:00:00">2:00 PM</option>
                                <option value="15:00:00">3:00 PM</option>
                            </Form.Control>
                        </Form.Group>
                        <Button variant="primary" type="submit" className="mt-1">
                            Book Appointment
                        </Button>
                    </Form>
                </Col>
            </Row>
            {/* <Row>
                <Col md={4}>
                    {
                        user === null ?
                            <>
                                <h1>Please login to book an appoiment</h1>
                                <Link className="nav-link text-danger" to="/login">Login right here</Link>
                            </> :
                            <>
                                <Form onSubmit={handleSubmit}>
                                    <Form.Group controlId="createdDate">
                                        <Form.Label>Select time</Form.Label>
                                        <Form.Control
                                            type="datetime-local"
                                            name="createdDate"
                                            value={selectedDate.toISOString().slice(0, 16)}
                                            onChange={handleDateChange}
                                        />
                                    </Form.Group>
                                    <Button variant="primary" type="submit" className='mt-1 mb-1'>
                                        Submit
                                    </Button>
                                </Form>
                            </>
                    }
                </Col>
            </Row> */}
        </Container>
    );
};

export default Appointment;
