import React, { useState } from "react";
import { Form, Button, Container, Row, Col } from "react-bootstrap";
import Navbar from "../components/navbar"; // Make sure to include your Navbar if needed

const FeedbackForm = () => {
  const user = JSON.parse(sessionStorage.user); // Assuming user data is stored in sessionStorage
  const [feedback, setFeedback] = useState({
    rating: "",
    comments: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFeedback({ ...feedback, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const feedbackData = {
      name: user.name,
      email: user.email,
      ...feedback,
    };
    // Here you can handle the form submission, e.g., send the feedback to an API
    console.log(feedbackData);
    // Reset the form after submission
    setFeedback({
      rating: "",
      comments: "",
    });
  };

  return (
    <>
      <Navbar />
      <Container className="mt-5">
        <Row className="justify-content-md-center">
          <Col md={6}>
            <h2 className="text-center mb-4">Feedback Form</h2>
            <Form onSubmit={handleSubmit}>
              <Form.Group className="mb-3">
                <Form.Label>Rating</Form.Label>
                <Form.Control
                  as="select"
                  name="rating"
                  value={feedback.rating}
                  onChange={handleChange}
                >
                  <option value="">Select rating</option>
                  <option value="1">1 - Very Poor</option>
                  <option value="2">2 - Poor</option>
                  <option value="3">3 - Average</option>
                  <option value="4">4 - Good</option>
                  <option value="5">5 - Excellent</option>
                </Form.Control>
              </Form.Group>

              <Form.Group className="mb-3">
                <Form.Label>Comments</Form.Label>
                <Form.Control
                  as="textarea"
                  name="comments"
                  value={feedback.comments}
                  onChange={handleChange}
                  rows={3}
                  placeholder="Enter your comments"
                />
              </Form.Group>

              <div className="text-center">
                <Button variant="primary" type="submit">
                  Submit
                </Button>
              </div>
            </Form>
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default FeedbackForm;
