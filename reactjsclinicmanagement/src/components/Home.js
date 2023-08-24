import React from "react";
import { Container, Row, Col, Card, Button } from "react-bootstrap";
import MySpinner from "../layout/MySpinner";
import { useNavigate } from "react-router-dom";

const Home = () => {
    const nav = useNavigate();

    const handleBooking = () => {
        nav("/create-appointment");
    }

    return (
        <Container>
            <Row>
                <Col>
                    <h1 className="text text-center text-info">Welcome to My Clinic</h1>
                </Col>
            </Row>
            <Row>
                <Col>
                    <MySpinner />
                </Col>
            </Row>
            <Row>
                <Col md={6}>
                    <Card>
                        <Card.Img variant="top" src="https://i.pinimg.com/564x/ee/d8/19/eed8191a10a1a2beb74a3526a2d2344d.jpg" alt="Service" />
                        <Card.Body>
                            <Card.Title>Our Services</Card.Title>
                            <Card.Text>
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla
                                vitae elit libero, a pharetra augue. Praesent commodo cursus
                                magna, vel scelerisque nisl consectetur.
                            </Card.Text>
                            <Button variant="primary">Learn More</Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={6}>
                    <Card>
                        <Card.Img variant="top" src="https://i.pinimg.com/564x/48/af/00/48af003b2309043ff74665d96391d32f.jpg" alt="Appointment" />
                        <Card.Body>
                            <Card.Title>Book an Appointment</Card.Title>
                            <Card.Text>
                                Ready to book an appointment with us? Lorem ipsum dolor sit
                                amet, consectetur adipiscing elit.
                            </Card.Text>
                            <Button variant="success" onClick={handleBooking}>Book Now</Button>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
};

export default Home;
