import { FaCheckCircle } from "react-icons/fa";

interface StepIndicatorProps {
  currentStep: number;
  steps: string[];
}

const StepIndicator = ({ currentStep, steps }: StepIndicatorProps) => {
  return (
    <div
      className="d-flex justify-content-between align-items-center mb-5"
      style={{ maxWidth: 600 }}
    >
      {steps.map((label, index) => {
        const isCompleted = currentStep > index + 1;
        const isActive = currentStep === index + 1;
        return (
          <div
            key={index}
            className="flex-fill text-center position-relative"
            style={{ minWidth: 80 }}
          >
            <div
              className={`mx-auto d-flex align-items-center justify-content-center shadow`}
              style={{
                width: 48,
                height: 48,
                borderRadius: "50%",
                background: isCompleted
                  ? "linear-gradient(135deg, #4fd1c5 0%, #38b2ac 100%)"
                  : isActive
                  ? "linear-gradient(135deg, #63b3ed 0%, #4299e1 100%)"
                  : "#f7fafc",
                color: isCompleted || isActive ? "#fff" : "#a0aec0",
                fontWeight: "bold",
                fontSize: 22,
                border: isActive ? "3px solid #4299e1" : "2px solid #e2e8f0",
                transition: "all 0.3s",
                boxShadow: isActive ? "0 0 0 4px #bee3f8" : "none",
              }}
            >
              {isCompleted ? <FaCheckCircle size={28} /> : index + 1}
            </div>
            <div
              style={{
                fontSize: 14,
                color: isActive ? "#4299e1" : "#718096",
                fontWeight: isActive ? 600 : 400,
              }}
            >
              {label}
            </div>
            {index < steps.length - 1 && (
              <div
                className="position-absolute"
                style={{
                  top: 24,
                  right: -40,
                  width: 80,
                  height: 4,
                  background: isCompleted
                    ? "linear-gradient(90deg, #4fd1c5 0%, #38b2ac 100%)"
                    : "#e2e8f0",
                  zIndex: 0,
                  borderRadius: 2,
                }}
              />
            )}
          </div>
        );
      })}
    </div>
  );
};

export default StepIndicator;
