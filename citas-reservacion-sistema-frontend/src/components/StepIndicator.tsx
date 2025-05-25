interface StepIndicatorProps {
  currentStep: number;
  steps: string[];
}

const StepIndicator = ({ currentStep, steps }: StepIndicatorProps) => {
  return (
    <div className="d-flex justify-content-between mb-4">
      {steps.map((label, index) => (
        <div key={index} className="text-center flex-fill">
          <div
            className={`rounded-circle mx-auto mb-2 ${
              currentStep === index + 1
                ? "bg-success text-white"
                : "bg-light border text-dark"
            }`}
            style={{ width: "40px", height: "40px", lineHeight: "40px" }}
          >
            {index + 1}
          </div>
          <small>{label}</small>
        </div>
      ))}
    </div>
  );
};

export default StepIndicator;
